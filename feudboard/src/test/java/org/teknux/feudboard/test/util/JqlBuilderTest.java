package org.teknux.feudboard.test.util;

import org.junit.Assert;
import org.junit.Test;
import org.teknux.feudboard.util.JqlBuilder;

import java.util.Arrays;


/**
 * @author Francois EYL
 */
public class JqlBuilderTest {

    @Test
    public void testProject() {
        Assert.assertEquals("project='project1'", JqlBuilder.get().project("project1").build());
    }

    @Test
    public void testFixVersion() {
        Assert.assertEquals("fixVersion='v1'", JqlBuilder.get().fixVersion("v1").build());
    }

    @Test
    public void testIssueType() {
        Assert.assertEquals("type='bug'", JqlBuilder.get().issueType("bug").build());
    }

    @Test
    public void testIssueType2() {
        Assert.assertEquals("type='bug2'", JqlBuilder.get().issueTypes(Arrays.asList(new String[] { "bug2"})).build());
    }

    @Test
    public void testIssueTypes() {
        Assert.assertEquals("(type='bug' OR type='story')", JqlBuilder.get().issueTypes(Arrays.asList(new String[] { "bug", "story"})).build());
    }

    @Test
    public void testProjectAndVersion() {
        String jql = JqlBuilder.get()
                .project("proj")
                .fixVersion("v")
                .build();

        Assert.assertEquals("project='proj' AND fixVersion='v'", jql);
    }

    @Test
    public void testProjectAndTypes() {
        String jql = JqlBuilder.get()
                .project("proj")
                .issueTypes(Arrays.asList(new String[] { "bug", "story"}))
                .build();

        Assert.assertEquals("project='proj' AND (type='bug' OR type='story')", jql);
    }

    @Test
    public void testAll() {
        String jql = JqlBuilder.get()
                .project("proj")
                .fixVersion("v")
                .issueTypes(Arrays.asList(new String[] { "bug", "story"}))
                .build();

        Assert.assertEquals("project='proj' AND fixVersion='v' AND (type='bug' OR type='story')", jql);
    }
}
