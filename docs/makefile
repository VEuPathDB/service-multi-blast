DOT_FILES = $(wildcard graphs/*.dot)
SVG_FILES = $(DOT_FILES:graphs/%.dot=assets/%.svg)

.PHONY: all
all: $(SVG_FILES) index.html

assets/%.svg: graphs/%.dot
	@echo "Rebuilding $@"
	@dot -Tsvg $< > $@

index.html: index.adoc
	@echo "Rebuilding index.html"
	@asciidoctor index.adoc