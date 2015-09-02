(ns iifym-tracker-api.handler-test
  (:require [clojure.data.json :as json]
            [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [iifym-tracker-api.handler :refer :all])
  (:use org.httpkit.fake))

(deftest handler-test
  (testing "headers"
    (let [response (app (mock/request :get "/"))]
      (is (= (:status response) 200))
      (is (= (:headers response) {"Content-Type" "application/json; charset=utf-8"
                                  "Access-Control-Allow-Origin" "*"
                                  "Access-Control-Allow-Methods" "GET"}))))

  (testing "not-found route"
    (let [response (app (mock/request :get "/invalid"))]
      (is (= (:status response) 404))))

  (testing "search"
    (let [mock-response {:body (slurp "test/fixtures/usda/search.json")}]
      (with-fake-http ["http://api.nal.usda.gov/ndb/search" mock-response]
        (let [response (app (mock/request :get "/search" {:query-params {:q "butter"}}))
              expected-body (json/read-str
                              (slurp "test/fixtures/usda/expected/search.json"))]
          (is (= (:status response) 200))
          (is (= (json/read-str (:body response)) expected-body))))))

  (testing "nutrients"
    (let [mock-response {:body (slurp "test/fixtures/usda/nutrients.json")}]
      (with-fake-http ["http://api.nal.usda.gov/ndb/reports" mock-response]
        (let [response (app (mock/request :get "/nutrients/123"))
              expected-body (json/read-str
                              (slurp "test/fixtures/usda/expected/nutrients.json"))]
          (is (= (:status response) 200))
          (is (= (json/read-str (:body response)) expected-body)))))))

