default:
	@echo "what are you doing?"

blast:
	@docker run -it --rm -v ${PWD}/blastdb:/blastdb:ro ncbi/blast bash