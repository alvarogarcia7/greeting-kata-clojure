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

(defmulti addressing (fn [name] (coll? name)) :default name)
(defmethod addressing true [names] (str/join " and " names))
(defmethod addressing false [name] name)

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


