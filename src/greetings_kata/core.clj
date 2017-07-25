(ns greetings-kata.core
  (:gen-class)
  (:require [clojure.string :as str]))

(defn
  greet
  [name]
  (letfn [(upper-case? [line] (and (> (count line) 0) (every? #(Character/isUpperCase %) line)))
          (anonymous? [name] (nil? name))
          (base-greeting [rest] (str "Hello, " rest "."))
          (anonymous-greeting [_] (base-greeting "my friend"))
          (personalized-greeting [name] (base-greeting name))]
    (let [
          greeting (if (anonymous? name) anonymous-greeting personalized-greeting)
          greeting (if (if (coll? name) (every? upper-case? name) (upper-case? name)) (comp str/upper-case greeting) greeting)
          names (if (coll? name) (str/join " and " name) name)
          ]
      (greeting names))))


