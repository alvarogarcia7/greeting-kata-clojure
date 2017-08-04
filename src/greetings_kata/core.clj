(ns greetings-kata.core
  (:gen-class)
  (:require [clojure.string :as str]))

(defn
  upper-case?
  [line]
  (and (> (count line) 0) (every? #(Character/isUpperCase %) line)))

(defn greet-multiple
  [all-names union last-union]
  (cond
    (= 1 (count all-names)) (first all-names)
    (= 2 (count all-names)) (str/join (str " " last-union) all-names)
    :else (str/join (str union last-union) [(str/join union (butlast all-names)) (last all-names)])))


(defn
  informal-greeting
  [names]
  (greet-multiple names ", " "AND ")
  )

(defn formal-greeting
  [names]
  (greet-multiple names ", " "and "))

(defn
  prepare-greetings
  [names]
  (let [{formal-names false informal-names true} (group-by upper-case? names)
        both-styles (and (not (nil? formal-names)) (not (nil? informal-names)))]
    {:formal
     {:lower (if (every? #(= nil %) names) ["my friend"] formal-names)
      :upper (if (not both-styles) informal-names)}
     :informal
     {:names (if both-styles informal-names [])}}))

(defn
  greet
  [name]
  (letfn [(as-coll [x] (if (coll? x) x [x]))
          (process? [coll] (not (empty? coll)))
          (greet [m]
            (str
              (let [{lower :lower upper :upper} (get m :formal)]
                (str
                  (if (process? lower) (str "Hello, " (formal-greeting lower) "."))
                  (if (process? upper) (str "HELLO, " (informal-greeting upper) "."))))
              (let [names (get-in m [:informal :names])]
                (if (process? names) (str " AND HELLO " (informal-greeting names) "!")))
              ))]
    (->> name
         as-coll
         prepare-greetings
         greet)))


