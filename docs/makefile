.PHONY: all
all: index.html \
     assets/sub-job-submission.svg \
     assets/blast-submission.svg

assets/sub-job-submission.svg: graphs/sub-job-submission.dot
	@echo "Updating sub-job submission graph"
	@dot -Tsvg graphs/sub-job-submission.dot > assets/sub-job-submission.svg

assets/blast-submission.svg: graphs/blast-submission.dot
	@echo "Updating blast submission graph"
	@dot -T svg graphs/blast-submission.dot > assets/blast-submission.svg

index.html: index.adoc
	@echo "Rebuilding index.html"
	@asciidoctor index.adoc