(ns stand.core
  (:require [re-frame.core :refer [clear-subscription-cache!
                                   dispatch
                                   dispatch-sync
                                   subscribe]]
            [reagent.core :as reagent]
            [reagent.dom :as rdom]

            [stand.events :as events]
            [stand.views :as views]
            [stand.config :as config]))

(enable-console-print!)

(defn dev-setup []
  (when config/debug?
    (println "dev mode")))

(defn root-component []
  [:dev
   [:h1 "Hello"]])

(defn mount-root []
  (clear-subscription-cache!)
  (rdom/render
   [root-component]
   (js/document.getElementById "app-container")))

(defn ^:export init []
  (dispatch-sync [::events/initialize-db])
  (mount-root)
  (dispatch [::events/init]))
