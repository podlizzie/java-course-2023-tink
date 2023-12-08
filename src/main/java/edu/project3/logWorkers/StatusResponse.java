package edu.project3.logWorkers;

public enum StatusResponse {
    OK(200),
    CREATED(201),
    ACCEPTED(202),
    NO_CONTENT(204),
    RESET_CONTENT(205),
    PARTIAL_CONTENT(206),

    MOVED_PERMANENTLY(301),
    FOUND(302),
    SEE_OTHER(303),
    NOT_MODIFIED(304),
    TEMPORARY_REDIRECT(307),

    BAD_REQUEST(400),
    UNAUTHORIZED(401),
    FORBIDDEN(403),
    NOT_FOUND(404),
    METHOD_NOT_ALLOWED(405),
    CONFLICT(409),
    GONE(410),
    REQUESTED_RANGE_NOT_SATISFIABLE(416),

    INTERNAL_SERVER_ERROR(500),
    NOT_IMPLEMENTED(501),
    BAD_GATEWAY(502),
    SERVICE_UNAVAILABLE(503),
    GATEWAY_TIMEOUT(504),
    HTTP_VERSION_NOT_SUPPORTED(505);

    private final int value;
    private static final String ERROR_MSG = "Invalid status code: %s";

    StatusResponse(int value) {
        this.value = value;
    }

    public static StatusResponse getByValue(int value) {
        for (StatusResponse status : values()) {
            if (status.value == value) {
                return status;
            }
        }
        throw new IllegalArgumentException(String.format(ERROR_MSG, value));
    }
}
