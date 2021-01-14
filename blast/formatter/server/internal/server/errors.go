package server

const (
	ErrfJobNotFound = "no job found with the hash %s"
)

type Error struct {
	Code      uint16 `json:"type"`
	Message   string `json:"message"`
	RequestID string `json:"requestID"`
}

func New400Error(message, requestID string) Error {
	return Error{
		Code:      400,
		Message:   message,
		RequestID: requestID,
	}
}

func New404Error(message, requestID string) Error {
	return Error{
		Code:      404,
		Message:   message,
		RequestID: requestID,
	}
}

func New500Error(message, requestID string) Error {
	return Error{
		Code:      500,
		Message:   message,
		RequestID: requestID,
	}
}
