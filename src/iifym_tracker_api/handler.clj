(ns iifym-tracker-api.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
            [cheshire.core :as json]))

(defroutes app-routes
  (GET "/" [] (json/generate-string {:asd {:fgh "jlk"}}))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes (assoc api-defaults :proxy true)))
