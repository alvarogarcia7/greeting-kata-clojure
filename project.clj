(defproject greetings-kata "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.8.0"]]
  :main ^:skip-aot greetings-kata.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}
             :dev {
                   :dependencies [[midje "1.6.3"]]
                   :plugins      [[lein-midje "3.1.3"]
                                  [com.jakemccrary/lein-test-refresh "0.6.0"]]}})
