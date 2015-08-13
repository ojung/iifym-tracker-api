(ns iifym-tracker-api.tools.migration
  (:require [ragtime.jdbc :as jdbc]
            [ragtime.repl :as repl]))

(defn load-config []
  {:database   (jdbc/sql-database "jdbc:postgresql://localhost/iifymtracker")
   :migrations (jdbc/load-resources "migrations")})

(defn migrate []
  (repl/migrate (load-config)))

(defn rollback []
  (repl/rollback (load-config)))
