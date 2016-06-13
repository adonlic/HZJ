package hr.fer.ppj.projekt.hzj.core.cache;

import java.util.List;

import hr.fer.ppj.projekt.hzj.core.models.business.Section;

/**
 * Created by ANTE on 12.6.2016..
 */
public class SectionsCache {
    private static List<Section> sections;

    public static List<Section> getSections() {
        return sections;
    }

    public static void setSections(List<Section> sections) {
        SectionsCache.sections = sections;
    }
}
