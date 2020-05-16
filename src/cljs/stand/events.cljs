(ns stand.events
  (:require
   [re-frame.core :refer [reg-event-db]]
   [stand.db :as db]))

(reg-event-db
 ::initialize-db
 (fn [_ _]
   db/default-db))

(reg-event-db
 ::init
 (fn [db _]
   db))
