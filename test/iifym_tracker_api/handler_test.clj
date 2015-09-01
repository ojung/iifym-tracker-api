(ns iifym-tracker-api.handler-test
  (:require [cheshire.core :as json]
            [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [iifym-tracker-api.handler :refer :all]))

;(deftest test-app
  ;(testing "main route"
    ;(let [response (app (mock/request :get "/"))
          ;expected-body (json/generate-string [])]
      ;(is (= (:status response) 200))
      ;(is (= (:headers response) {"Content-Type" "application/json; charset=utf-8"
                                  ;"Access-Control-Allow-Origin" "*"
                                  ;"Access-Control-Allow-Methods" "GET"}))
      ;(is (= (:body response) expected-body))))

  ;(testing "not-found route"
    ;(let [response (app (mock/request :get "/invalid"))]
      ;(is (= (:status response) 404)))))

  ;(testing "search route"
    ;(let [response (app (mock/request :get "/search"))
          ;expected-body (json/generate-string ["search"])]
      ;(is (= (:status response) 200))
      ;(is (= (:headers response) {"Content-Type" "application/json; charset=utf-8"
                                  ;"Access-Control-Allow-Origin" "*"
                                  ;"Access-Control-Allow-Methods" "GET"}))
      ;(is (= (:body response) expected-body))))
