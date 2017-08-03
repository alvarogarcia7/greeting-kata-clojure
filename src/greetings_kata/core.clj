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
  different-case
  [coll]
  (apply not= (map upper-case? coll)))

(defn greet-multiple
  [all-names union last-union]
  (cond
    (= 1 (count all-names)) (first all-names)
    (= 2 (count all-names)) (str/join (str " " last-union) all-names)
    :else (str/join (str union last-union) [(str/join union (butlast all-names)) (last all-names)]))
  )

(defn
  informal-greeting
  [names]
  (let [informal-names (filter upper-case? names)]
    (str " AND HELLO " (greet-multiple informal-names ", " "AND ") "!")))

(defn addressing-multiple
  ""
  [all-names]
  (str
    (greet-multiple all-names ", " "and ")
    "."
    ))

(defn
  addressing-mixed
  [names]
  (let [are-mixed-greetings (different-case names)
        informal-names (filter upper-case_? names)
        formal-names (filter (comp not upper-case_?) names)]
    (str
      (addressing-multiple formal-names)
      (if are-mixed-greetings
        (informal-greeting informal-names)))))


(defmulti addressing (fn [name] (coll? name)) :default name)
(defmethod addressing false [name] (str name "."))
(defmethod addressing true [names] (if (different-case names)
                                     (addressing-mixed names)
                                     (addressing-multiple names)))


(defn
  greet
  [name]
  (letfn [#_(in-requests [names] (group-by upper-case_? names))
          (anonymous? [name] (nil? name))
          (base-greeting [rest] (str "Hello, " rest))
          (anonymous-greeting [_] (base-greeting "my friend."))
          (personalized-greeting [name] (base-greeting name))
          ]
    (let [greeting (if (anonymous? name) anonymous-greeting personalized-greeting)
          greeting (if (upper-case? name) (comp str/upper-case greeting) greeting)
          names (addressing name)]
      (greeting names))))


