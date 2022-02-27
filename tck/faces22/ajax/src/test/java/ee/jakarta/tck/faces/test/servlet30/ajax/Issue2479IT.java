/*
 * Copyright (c) 1997, 2018 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0, which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the
 * Eclipse Public License v. 2.0 are satisfied: GNU General Public License,
 * version 2 with the GNU Classpath Exception, which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 */

package ee.jakarta.tck.faces.test.servlet30.ajax; 

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import com.gargoylesoftware.htmlunit.html.HtmlSpan;

import ee.jakarta.tck.faces.test.util.arquillian.ITBase;
import jakarta.faces.component.behavior.AjaxBehavior;

public class Issue2479IT extends ITBase {

    /**
     * @see AjaxBehavior
     * @see https://github.com/eclipse-ee4j/mojarra/issues/2483
     */
    @Test
    public void testSelectDataTable() throws Exception {
        HtmlPage page = getPage("selectOneMenuDataTable.xhtml");
        HtmlSpan span1 = (HtmlSpan)page.getElementById("table:0:inCity");
        assertTrue((span1.asNormalizedText()).equals("alpha"));
        HtmlSpan span2 = (HtmlSpan)page.getElementById("table:1:inCity");
        assertTrue((span2.asNormalizedText()).equals("alpha"));
        HtmlSpan span3 = (HtmlSpan)page.getElementById("table:2:inCity");
        assertTrue((span3.asNormalizedText()).equals("alpha"));
        HtmlSelect select = (HtmlSelect)page.getElementById("selectMenu");
        page = (HtmlPage) select.setSelectedAttribute("beta",true);
        webClient.waitForBackgroundJavaScript(3000);
        span1 = (HtmlSpan)page.getElementById("table:0:inCity");
        assertTrue((span1.asNormalizedText()).equals("beta"));
        span2 = (HtmlSpan)page.getElementById("table:1:inCity");
        assertTrue((span2.asNormalizedText()).equals("beta"));
        span3 = (HtmlSpan)page.getElementById("table:2:inCity");
        assertTrue((span3.asNormalizedText()).equals("beta"));
    }
}

