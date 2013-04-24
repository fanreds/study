package pl.edu.pk.web;

import org.jboss.seam.international.Alter;
import org.jboss.solder.core.Client;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Instance;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * User: klex
 * Date: 4/23/13
 * Time: 11:39 AM
 */
@Named
@SessionScoped
public class LocaleSelector implements Serializable{

    List<Locale> availableLocales;

    @Inject
    @Alter
    @Client
    private Event<Locale> localeChangeEvent;

    @Inject
    @Client
    private Instance<Locale> userLocale;

    public List<Locale> getAvailableLocales() {
        if (availableLocales==null){
            availableLocales=new ArrayList<Locale>();
            Iterator<Locale> iterator = FacesContext.getCurrentInstance().getApplication().getSupportedLocales();
            while (iterator.hasNext()){
                availableLocales.add(iterator.next());
            }
        }
        return availableLocales;
    }

    public Locale getSelectedLocale()
    {
        return userLocale.get();
    }

    public void setSelectedLocale(Locale locale)
    {
        localeChangeEvent.fire(locale);
    }

    public Converter getConverter() {
        return converter;
    }

    private Converter converter = new Converter() {
        @Override
        public Object getAsObject(FacesContext context, UIComponent component, String value)
        {
            return new Locale(value);
        }

        @Override
        public String getAsString(FacesContext context, UIComponent component, Object value)
        {
            return value == null ? null : value.toString();
        }
    };
}
