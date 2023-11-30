package subway.domain.dto;

public final class FindPathDto {
    private final String src;
    private final String dest;

    public FindPathDto(
            final String src,
            final String dest
    ) {
        this.src = src;
        this.dest = dest;
    }

    public String getSrc() {
        return src;
    }

    public String getDest() {
        return dest;
    }
}

