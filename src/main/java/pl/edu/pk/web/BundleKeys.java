package pl.edu.pk.web;

import org.jboss.seam.international.status.builder.BundleKey;

/**
 * Created with IntelliJ IDEA.
 * User: klex
 * Date: 4/23/13
 * Time: 11:26 AM
 */
public interface BundleKeys {
    BundleKey SAVE_ACCESS_CONFIRMATION = new BundleKey(BundleNames.view.name(),"view.save.access.info");
    BundleKey SHARE_ACCESS_ALL = new BundleKey(BundleNames.view.name(),"view.shareall.information");
    BundleKey SHARE_ACCESS_ONLY_SPECIALIZATION = new BundleKey(BundleNames.view.name(),"view.specialization.information");
    BundleKey SHARE_ACCESS_ONLY_GROUP = new BundleKey(BundleNames.view.name(),"view.group.information");
    BundleKey SHARE_ACCESS_SELECTED_USERS  = new BundleKey(BundleNames.view.name(),"view.student.information");
    BundleKey MESSAGE_SENDED  = new BundleKey(BundleNames.view.name(),"view.user.message.send");
    BundleKey PASSWORD_CHANGED  = new BundleKey(BundleNames.view.name(),"view.user.password.change");
    BundleKey MESSAGE_RECIPIENT_NOT_SELECTED  = new BundleKey(BundleNames.view.name(),"view.message.recipient.nonselected");
}
