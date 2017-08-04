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
  (greet-multiple informal-names ", " "AND ")
  )

(defn formal-greeting
  [all-names]
  (greet-multiple all-names ", " "and "))

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

;{:formal
;           {:lower []
;            :upper []
;            }
; :informal []}

(defn
  greet
  [name]
  (letfn [#_(in-requests [names] (group-by upper-case_? names))
          (as-coll [x] (if (coll? x) x [x]))
          (prepare-greetings [names]
            (let [{formal-names false informal-names true} (group-by upper-case? names)
                  both-styles (and (not (nil? formal-names)) (not (nil? informal-names)))]
              {:formal
               {:lower (if (every? #(= nil %) names) ["my friend"] formal-names)
                :upper (if (not both-styles) informal-names)}
               :informal
               {:names (if both-styles informal-names [])}}))
          ]
    (let [names (as-coll name)
          m (prepare-greetings names)]
      (str
        (let [{lower :lower upper :upper} (get m :formal)]
          (str
            (if (not (empty? lower)) (str "Hello, " (formal-greeting lower) "."))
            (if (not (empty? upper)) (str "HELLO, " (informal-greeting upper) "."))))
        (let [names (get-in m [:informal :names])]
          (if (not (empty? names)) (str " AND HELLO " (informal-greeting names) "!")))
        ))))


