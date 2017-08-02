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

(defn
  informal-greeting
  [names]
  (let [informal-names (filter upper-case? names)]
    (str " AND HELLO " (str/join " AND " informal-names) "!")))

(defn
  addressing-multiple-names
  [names]
  (let [are-mixed-greetings (different-case names)
        all-names names
        informal-names (filter upper-case_? names)
        formal-names (filter (comp not upper-case_?) names)]
    (if are-mixed-greetings
      (str
        (if (= (count names) 2)
          (str/join " and " formal-names)
          (str/join ", and " [(str/join ", " (butlast formal-names)) (last formal-names)]))
        "."
        (informal-greeting informal-names))

      (str
        (if (= (count names) 2)
          (str/join " and " names)
          (str/join ", and " [(str/join ", " (butlast all-names)) (last all-names)]))
        "."
      ))))

(defmulti addressing (fn [name] (coll? name)) :default name)
(defmethod addressing false [name] (str name "."))
(defmethod addressing true [names] (addressing-multiple-names names))


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


