package fq

type Status string

const (
	StatusSuccess          Status = "success"
	StatusFailure          Status = "failure"
	StatusPermanentFailure Status = "permanent-failure"
)
