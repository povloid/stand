(defproject stand "0.1.0"
  :license {:name ""
            :url  ""}
  :description "The Stand for testing"
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/clojurescript "1.10.597"]
                 [com.taoensso/timbre "4.10.0"]
                 [reagent "0.10.0" :exclusions [cljsjs/react
                                                cljsjs/react-dom
                                                cljsjs/react-dom-server]]
                 [re-frame "0.12.0"]
                 [binaryage/oops "0.7.0"]]

  :plugins [[lein-cljsbuild "1.1.7"]
            [lein-sass "0.5.0"]]

  :source-paths ["src"]

  :auto-clean false
  :clean-targets ^{:protect false} ["resources/public/js"
                                    "target"
                                    "test/js"
                                    "out"]

  :sass {:src              "sass"
         :output-directory "resources/public/css"}


  :figwheel {:http-server-root "public"
             :server-port      3443
             :nrepl-port       7883}

  :profiles {:dev {:dependencies [[binaryage/devtools "1.0.0"]
                                  [figwheel-sidecar "0.5.19"]
                                  [re-frisk "1.2.0"]]
                   :plugins      [[lein-figwheel "0.5.19"]
                                  [lein-doo "0.1.7"]]}}

  :cljsbuild {:builds [{:id           "dev"
                        :source-paths ["src/cljs"]
                        :figwheel     {:on-jsload "stand.core/mount-root"}
                        :compiler     {:main                 stand.core
                                       :output-to            "resources/public/js/app.js"
                                       :output-dir           "resources/public/js/out"
                                       :asset-path           "js/out"
                                       :source-map           true
                                       :source-map-timestamp true
                                       :optimizations        :none
                                       :pretty-print         true
                                       :npm-deps             false
                                       :foreign-libs         [{:file           "target/nodejs/bundle.js"
                                                               :provides       ["react"
                                                                                "react-dom"
                                                                                "react-dom-server"
                                                                                "react-select"
                                                                                "react-color"
                                                                                "react-transition-group"
                                                                                "cropperjs"]
                                                               :global-exports {"react"     "React"
                                                                                "react-dom" "ReactDOM"}}]
                                       :preloads             [re-frisk.preload devtools.preload]
                                       :external-config      {:devtools/config
                                                              {:features-to-install           :all
                                                               :dont-detect-custom-formatters true}}}}
                       {:id           "prod"
                        :source-paths ["src/cljs"]
                        :compiler     {:main            stand.core
                                       :output-to       "resources/public/js/app.js"
                                       :optimizations   :advanced
                                       :closure-defines {goog.DEBUG false}
                                       :cache-analysis  true
                                       :infer-externs   true
                                       :process-shim    false
                                       :npm-deps        false
                                       :foreign-libs    [{:file           "target/nodejs/bundle.js"
                                                          :provides       ["react"
                                                                           "react-dom"
                                                                           "react-dom-server"
                                                                           "react-select"
                                                                           "react-color"
                                                                           "react-transition-group"
                                                                           "cropperjs"]
                                                          :global-exports {"react"     "React"
                                                                           "react-dom" "ReactDOM"}}]}}]})
