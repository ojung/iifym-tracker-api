(ns iifym-tracker-api.connectors.usda
  (:require [clojure.data.json :as json]
            [org.httpkit.client :as http]))

(def options {:max "500"
              :offset "0"
              :api_key "1eIclhFT5755RFdmsXWqWwGav6nHpKTd8xASPzPg"
              :format "json"})

(def api-url "http://api.nal.usda.gov/ndb")
(def search-url (str api-url "/search"))
(def report-url (str api-url "/reports"))

(defn search [request]
  (let [term (:q (:params request))
        response (http/get search-url {:query-params (assoc options :q term)})
        search-results (json/read-str (:body @response) :key-fn keyword)]
    {:body (get-in search-results [:list :item] [])}))

(defn nutrients [id]
  (fn [request]
    (let [query-params {:query-params (merge options {:ndbno id :type "b"})}
          response (http/get report-url query-params)
          report (json/read-str (:body @response) :key-fn keyword)
          nutrient-list (get-in report [:report :food :nutrients])]
      {:body (map #(dissoc % "measures") nutrient-list)})))

