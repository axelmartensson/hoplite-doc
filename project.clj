(defproject hoplite-doc "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :plugins [[lein-cljsbuild "0.3.3"]]
  :dependencies [[org.clojure/clojure "1.6.0"] 
                 [org.clojure/clojurescript "0.0-2234"]
                 [tailrecursion/hoplon "5.10.24" :exclusions [org.clojure/clojure]]]
  :cljsbuild {:builds [{:id "hoplite-doc"
                        :source-paths ["src"]
                        :jar true
                        :compiler {
                                   :pretty-print false
                                   :output-to "main.js"
                                   :source-map "main.js.map"
                                   }}]})
