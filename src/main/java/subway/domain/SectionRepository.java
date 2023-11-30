package subway.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class SectionRepository {

    private static final List<Section> sections = new ArrayList<>();

    public static void addSection(final Section section) {
        sections.add(section);
    }

    public static List<Section> sections() {
        return Collections.unmodifiableList(sections);
    }

    public List<Section> findSectionsByPath(final List<Station> path) {
        final List<Section> foundSections = new ArrayList<>();

        for (int i = 0; i < path.size() - 1; i++) {
            final Station srcStation = path.get(i);
            final Station destStation = path.get(i + 1);

            final Section section = sections.stream()
                    .filter(s -> s.getSrcStation().equals(srcStation))
                    .filter(s -> s.getDestStation().equals(destStation))
                    .findFirst()
                    .orElseThrow();
            foundSections.add(section);
        }
        return foundSections;
    }


}
