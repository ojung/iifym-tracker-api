(ns iifym-tracker-api.handler
  (:require [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
            [ring.middleware.json :refer [wrap-json-response]]
            [ring.middleware.logger :refer [wrap-with-logger]]
            [compojure.core :refer :all]
            [iifym-tracker-api.search]))

(defroutes app-routes
  (GET "/" []  {:body []})
  (GET "/search" [] iifym-tracker-api.search/search)
  (route/not-found "Not Found"))

(defn wrap-cors [handler]
  (fn [request]
    (let [response (handler request)]
      (-> response
        (assoc-in [:headers "Access-Control-Allow-Origin"] "*")
        (assoc-in [:headers "Access-Control-Allow-Methods"] "GET")))))

(def app
  (-> app-routes
      (wrap-with-logger)
      (wrap-json-response)
      (wrap-cors)
      (wrap-defaults (assoc api-defaults :proxy true))))
