(ns greetings-kata.core
  (:gen-class)
  (:require [clojure.string :as str]))

(defn
  upper-case?
  [line]
  (and (> (count line) 0) (every? #(Character/isUpperCase %) line)))

(defn
  all-upper-case?
  [names]
  (every? upper-case? names))

(defn
  different-case
  [coll]
  (apply not= (map upper-case? coll)))

(defn greet-multiple
  [all-names union last-union]
  (cond
    (= 1 (count all-names)) (first all-names)
    (= 2 (count all-names)) (str/join (str " " last-union) all-names)
    :else (str/join (str union last-union) [(str/join union (butlast all-names)) (last all-names)])))


(defn
  informal-greeting
  [informal-names]
  (str
    " AND HELLO "
    (greet-multiple informal-names ", " "AND ")
    "!"))

(defn formal-greeting
  [all-names]
  (str
    (greet-multiple all-names ", " "and ")
    "."))

(defn
  addressing-mixed
  [names]
  (let [are-mixed-greetings (different-case names)
        {formal-names false informal-names true} (group-by upper-case? names)]
    (str
      (formal-greeting formal-names)
      (informal-greeting informal-names))))


(defn
  addressing
  [names]
  (if (different-case names)
    (addressing-mixed names)
    (formal-greeting names)))


(defn
  greet
  [name]
  (letfn [#_(in-requests [names] (group-by upper-case_? names))
          (as-coll [x] (if (coll? x) x [x]))
          (anonymous? [names] (empty? name))
          (base-greeting [rest] (str "Hello, " rest))
          (anonymous-greeting [_] (base-greeting "my friend."))
          (personalized-greeting [name] (base-greeting name))
          ]
    (let [names (as-coll name)
          greeting (if (anonymous? names) anonymous-greeting personalized-greeting)
          greeting (if (all-upper-case? names) (comp str/upper-case greeting) greeting)
          names (addressing names)]
      (greeting names))))


