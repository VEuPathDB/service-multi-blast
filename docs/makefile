.PHONY: all
all: index.html \
     assets/sub-job-submission.svg \
     assets/blast-submission.svg \
     assets/blast-request-validation.svg \
     assets/blast-sequence-validation.svg \
     assets/blast-target-validation.svg

assets/sub-job-submission.svg: graphs/sub-job-submission.dot
	@echo "Updating sub-job submission graph"
	@dot -Tsvg graphs/sub-job-submission.dot > assets/sub-job-submission.svg

assets/blast-submission.svg: graphs/blast-submission.dot
	@echo "Updating blast submission graph"
	@dot -T svg graphs/blast-submission.dot > assets/blast-submission.svg

assets/blast-request-validation.svg: graphs/blast-request-validation.dot
	@echo "Updating blast request validation graph"
	@dot -T svg graphs/blast-request-validation.dot > assets/blast-request-validation.svg

assets/blast-sequence-validation.svg: graphs/blast-sequence-validation.dot
	@echo "Updating blast sequence validation graph"
	@dot -T svg graphs/blast-sequence-validation.dot > assets/blast-sequence-validation.svg

assets/blast-target-validation.svg: graphs/blast-target-validation.dot
	@echo "Updating blast target validation graph"
	@dot -T svg graphs/blast-target-validation.dot > assets/blast-target-validation.svg

index.html: index.adoc
	@echo "Rebuilding index.html"
	@asciidoctor index.adoc