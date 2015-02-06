(ns renderers
  (:require [clojure.zip :as z]))

(defn api-zipper [tree]
  (z/zipper #(:children %) #(:children %) #(assoc % :children %2) tree))

(defn walk 
  [tree matcher visitor]
  (loop [loc (api-zipper tree)] 
      (if (z/end? loc)
        (z/root loc)
        (recur (z/next (if (matcher (z/node loc))
                         (visitor loc)
                         loc))))))

(defn collect 
  [matcher tree]
  (loop [loc (api-zipper tree) nodes (list)] 
      (if (z/end? loc)
        nodes
        (recur (z/next loc) (if (matcher (z/node loc))
                              (cons (z/node loc) nodes)
                              nodes)))))

(defn visit-all [tree typ edit]
  (walk tree #(= typ (:type %)) edit))

(defn collect-all [typ tree]
  (collect #(= typ (:type %)) tree))

(defn drop-all [tree typ]
  (visit-all tree typ #(z/remove %)))

(defn endpoint-overview [tree endpoint-desc]
  (visit-all tree :endpoint #(z/edit % endpoint-desc)))

(defn section-overview [tree section-wrapper]
  (visit-all tree :section #(z/edit % section-wrapper)))

(defn overview [tree section-wrapper endpoint-desc]
  (-> tree
      (endpoint-overview endpoint-desc)
      (section-overview section-wrapper)
      (drop-all :response)
      :children))

