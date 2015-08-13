(defproject iifym-tracker-api "0.1.0-SNAPSHOT"
  :description "IFFYMTracker api"
  :url "https://lit-temple-5563.herokuapp.com/"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.3.1"]
                 [org.postgresql/postgresql "9.3-1102-jdbc41"]
                 [ragtime "0.4.0-SNAPSHOT"]
                 [ring/ring-defaults "0.1.2"]]
  :plugins [[lein-ring "0.8.13"]]
  :ring {:handler iifym-tracker-api.handler/app}
  :aliases {"migrate"  ["run" "-m" "iifym-tracker-api.tools.migration/migrate"]
            "rollback" ["run" "-m" "iifym-tracker-api.tools.migration/rollback"]}
  :profiles {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                                  [ring-mock "0.1.5"]]}})
