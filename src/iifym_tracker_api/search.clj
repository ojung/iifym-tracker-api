(ns iifym-tracker-api.search
  (:require [cheshire.core :as json]
            [org.httpkit.client :as http]))

(def options {:query-params {:max "100"
                             :offset "0"
                             :api_key "1eIclhFT5755RFdmsXWqWwGav6nHpKTd8xASPzPg"
                             :format "json"}})

(def api-url "http://api.nal.usda.gov/ndb/search")

(defn search [request]
  (let [term (:q (:params request))
        response (http/get api-url (assoc-in options [:query-params :q] term))
        search-results (json/parse-string (:body @response))]
    {:body (get-in search-results ["list" "item"] [])}))
