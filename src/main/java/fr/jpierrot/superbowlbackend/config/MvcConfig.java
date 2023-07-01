package fr.jpierrot.superbowlbackend.config;

import fr.jpierrot.superbowlbackend.presentation.controllers.MvcRegistration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController(MvcRegistration.ROOT).setViewName("/html/admin.html");
        registry.addViewController(MvcRegistration.ROOT + MvcRegistration.TEAMS).setViewName("/html/team-list.html");
        registry.addViewController(MvcRegistration.ROOT + MvcRegistration.TEAMS + "/new").setViewName("./html/new-team.html");
        registry.addViewController(MvcRegistration.ROOT + MvcRegistration.TEAMS + "/update").setViewName("./html/update-team.html");
        registry.addViewController(MvcRegistration.ROOT + MvcRegistration.PLAYERS).setViewName("./html/player-list.html");
        registry.addViewController(MvcRegistration.ROOT + MvcRegistration.PLAYERS + "/new").setViewName("./html/new-player.html");
        registry.addViewController(MvcRegistration.ROOT + MvcRegistration.PLAYERS + "/update").setViewName("./html/update-player.html");
        registry.addViewController(MvcRegistration.ROOT + MvcRegistration.MATCHES).setViewName("./html/matches-list.html");
        registry.addViewController(MvcRegistration.ROOT + MvcRegistration.MATCHES + "/new").setViewName("./html/new-matches.html");
        registry.addViewController(MvcRegistration.ROOT + MvcRegistration.MATCHES + "/update").setViewName("./html/update-matches.html");
    }




}
