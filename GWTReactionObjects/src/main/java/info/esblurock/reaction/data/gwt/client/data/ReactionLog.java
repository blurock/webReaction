/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package info.esblurock.reaction.data.gwt.client.data;

import java.util.logging.Logger;

/**
 *
 * @author blurock
 */
public class ReactionLog {
        public static void logInfo(String msg) {
            java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.INFO,msg);
        }
        public static void logError(String msg) {
            java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE,msg);
        }
}
