package name.imh0t3mp.course.geekbrains.config;

import org.mapstruct.ReportingPolicy;

@org.mapstruct.MapperConfig(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public class MapperConfig {
}
