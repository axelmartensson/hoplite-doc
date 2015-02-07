(ns hoplite-doc.tags
  (:require 
    [tailrecursion.hoplon :refer :all]))

(defn api [& sections]
  {:type :api :children sections})

(defn section [name & endpoints]
  {:type :section :name name :children endpoints})

(defn endpoint [request short-d long-d & responses]
  {:type :endpoint
   :request request
   :short-d short-d
   :long-d long-d
   :children responses})

(defn GET [route]
  (div "GET " route))

(defn PUT [route obj]
  (div "PUT " route "\n\n" obj))

(defn POST [route obj]
  (div "POST " route "\n\n" obj))


(defn response [code content]
  {:type :response :code code :content content})
;; can make a defnode macro

