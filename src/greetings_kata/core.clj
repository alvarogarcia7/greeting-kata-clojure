(ns greetings-kata.core
  (:gen-class)
  (:require [clojure.string :as str]))

(defn
  upper-case_?
  [line]
  (and (> (count line) 0) (every? #(Character/isUpperCase %) line)))

(defmulti upper-case? (fn [name] (coll? name)) :default name)
(defmethod upper-case? true [names] (every? upper-case_? names))
(defmethod upper-case? false [name] (upper-case_? name))

(defn
  join__
  [names]
  (if (= (count names) 2)
    (str/join " and " names)
    (str/join ", and " [(str/join ", " (butlast names)) (last names)])))

(defmulti addressing (fn [name] (coll? name)) :default name)
(defmethod addressing false [name] name)
(defmethod addressing true [names]
  (join__ names))

(defn
  greet
  [name]
  (letfn [(anonymous? [name] (nil? name))
          (base-greeting [rest] (str "Hello, " rest "."))
          (anonymous-greeting [_] (base-greeting "my friend"))
          (personalized-greeting [name] (base-greeting name))]
    (let [greeting (if (anonymous? name) anonymous-greeting personalized-greeting)
          greeting (if (upper-case? name) (comp str/upper-case greeting) greeting)
          names (addressing name)]
      (greeting names))))


