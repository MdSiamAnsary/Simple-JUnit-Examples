[![The Baeldung logo](https://www.baeldung.com/wp-content/themes/baeldung/icon/logo.svg)](/ "Baeldung") [](# "Navigation")

-   [](#)
-   [![The Baeldung Logo](https://www.baeldung.com/wp-content/themes/baeldung/icon/logo.svg)](/ "Baeldung")
-   [Start Here](/start-here)
-   [Courses ▼▲](javascript:void(0))
    -   [](/rest-with-spring-course "The “REST With Spring” Course")

        ### REST with Spring

        The canonical reference for building a production grade API with Spring.

    -   [](/learn-spring-security-course)

        ### Learn Spring Security ▼▲

        THE unique Spring Security education if you’re working with Java today.

        -   [](/learn-spring-security-core-course)

            ### Learn Spring Security Core

            Focus on the Core of Spring Security 5

        -   [](/learn-spring-security-oauth-course)

            ### Learn Spring Security OAuth

            Focus on the new OAuth2 stack in Spring Security 5

    -   [](/learn-spring-course)

        ### Learn Spring

        From no experience to actually building stuff​.

-   [Guides ▼▲](javascript:void(0))
    -   [](/persistence-with-spring-series)

        ### Persistence

        The Persistence with Spring guides

    -   [](/rest-with-spring-series)

        ### REST

        The guides on building REST APIs with Spring

    -   [](/security-spring)

        ### Security

        The Spring Security guides

-   [About ▼▲](javascript:void(0))
    -   [](/full_archive)

        ### Full Archive

        The high level overview of all the articles on the site.

    -   [](/baeldung-ebooks)

        ### Baeldung Ebooks

        Discover all of our eBooks

    -   [](/about)

        ### About Baeldung

        About Baeldung.

-   [](/feed)
-   [](#search)

Unit Testing of System.out.println() with JUnit
===============================================

Last modified: August 13, 2020

by [Jonathan Cook](https://www.baeldung.com/author/jonathan-cook/ "Posts by Jonathan Cook")

-   [Java](https://www.baeldung.com/category/java/)+
-   [Testing](https://www.baeldung.com/category/testing/)

-   [JUnit](https://www.baeldung.com/tag/junit/)

Java Top

### **I just announced the new *Learn Spring* course, focused on the fundamentals of Spring 5 and Spring Boot 2:**

**[\>\> CHECK OUT THE COURSE](/ls-course-start)**

**1. Overview**
---------------

When unit testing we may occasionally want to test the messages that we write to [standard output](/linux/pipes-redirection#standard-io) via *System.out.println()*.

Although we'd generally [prefer](/java-system-out-println-vs-loggers) a [logging framework](/java-logging-intro) over direct interaction with standard output, sometimes this isn't possible.

In this quick tutorial, **we'll take a look at a couple of ways we can unit test *System.out.println()* using [JUnit](/tag/junit/).**

**2. A Simple Print Method**
----------------------------

Throughout this tutorial, the focus of our tests will be a simple method that writes to the standard output stream:

    private void print(String output) {
        System.out.println(output);
    }

A quick reminder that the *out* variable is a *public static final PrintStream* object which represents the [standard output stream](/java-lang-system) intended for system-wide usage.

**3. Working With Core Java**
-----------------------------

Now let's see how we can write a unit test to check the content of what we send to the *println* method. However, before we write our actual unit test, we'll need to provide some initialization in our test:

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

**In the *setUp* method, we reassign the standard output stream to a new *PrintStream* with a *ByteArrayOutputStream***. As we're going to see this output stream is where the values will now be printed:

    @Test
    void givenSystemOutRedirection_whenInvokePrintln_thenOutputCaptorSuccess() {
        print("Hello Baeldung Readers!!");
            
        Assert.assertEquals("Hello Baeldung Readers!!", outputStreamCaptor.toString()
          .trim());
    }

After we call the *print* method with the chosen text, we can then verify that the *outputStreamCaptor* contains the content we were expecting. **We call the *trim* method to remove the new line that *System.out.println()* adds.**

As the standard output stream is a shared static resource used by other parts of the system, **we should take care of restoring it to its original state when our test terminates:**

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

This ensures we don't get any unwanted side effects later on in other tests.

**4. Using System Rules**
-------------------------

In this section, **we'll take a look at a neat external library called [System Rules](https://stefanbirkner.github.io/system-rules/) which provides a set of [JUnit rules](/junit-4-rules) for testing code that uses the *System* class**.

Let's start by adding the [dependency](https://search.maven.org/classic/#search%7Cga%7C1%7Ca%3A%22system-rules%22) to our *pom.xml*:

    <dependency>
        <groupId>com.github.stefanbirkner</groupId>
        <artifactId>system-rules</artifactId>
        <version>1.19.0</version>
        <scope>test</scope>
    </dependency>

Now, we can go ahead and write a test using the *SystemOutRule* the library provides:

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Test
    public void givenSystemOutRule_whenInvokePrintln_thenLogSuccess() {
        print("Hello Baeldung Readers!!");

        Assert.assertEquals("Hello Baeldung Readers!!", systemOutRule.getLog()
          .trim());
    }

**Pretty cool! Using the *SystemOutRule,* we can intercept the writes to*****System.out**.*First, we start logging everything written to *System.out*by calling the *enableLog* method on our rule. Then we simply call *getLog*to get the text written to *System.out* since we called *enableLog*.

This rule also includes a handy method that returns a log that always has the line separator as *\\n*

    Assert.assertEquals("Hello Baeldung Readers!!\n", systemOutRule.getLogWithNormalizedLineSeparator());

**5. Using System Rules with JUnit5 and Lambdas**
-------------------------------------------------

In [JUnit5](/junit-5), the rules model was replaced by [extensions](/junit-5-extensions). Luckily, the System Rules library presented in the last section has a [variation](https://github.com/stefanbirkner/system-lambda) prepared to work with JUnit5.

System Lambda is available from [Maven Central](https://search.maven.org/classic/#search%7Cga%7C1%7Ca%3A%22system-lambda%22). So we can go ahead and add it to our *pom.xml*:

    <dependency>
        <groupId>com.github.stefanbirkner</groupId>
        <artifactId>system-lambda</artifactId>
        <version>1.0.0</version>
        <scope>test</scope>
    </dependency>

Now let's implement our test using this version of the library:

    @Test
    void givenTapSystemOut_whenInvokePrintln_thenOutputIsReturnedSuccessfully() throws Exception {

        String text = tapSystemOut(() -> {
            print("Hello Baeldung Readers!!");
        });

        Assert.assertEquals("Hello Baeldung Readers!!", text.trim());
    }

In this version, **we make use of the *tapSystemOut* method, which executes the statement and lets us capture the content passed to *System.out***.

**6. Conclusion**
-----------------

In this tutorial, we've learned about a couple of approaches for testing *System.out.println*. In the first approach, we saw how to redirect where we write the standard output stream using core Java.

Then we saw how to use a promising external library called System Rules using, first, JUnit 4 style rules and then later working with lambdas.

As always, the full source code of the article is available [over on GitHub](https://github.com/eugenp/tutorials/tree/master/testing-modules/testing-libraries).

Java bottom

### **I just announced the new *Learn Spring* course, focused on the fundamentals of Spring 5 and Spring Boot 2:**

**[\>\> CHECK OUT THE COURSE](/ls-course-end)**

2 Comments

**

**

Oldest **

Newest

** Inline Feedbacks

View all comments

![Richard](https://secure.gravatar.com/avatar/32d52074dafb06d72b583254d396b884?s=64&r=g)

Richard

** 2 months ago

**

[**](https://twitter.com/intent/tweet?text=Shouldn’t%20the%20tearDown%20method%20in%20section%203%20be%20annotated%20wit...%20&url=https%3A%2F%2Fwww.baeldung.com%2Fjava-testing-system-out-println%23comment-13846 "Share On Twitter")[**](https://vk.com/share.php?url=https://www.baeldung.com/java-testing-system-out-println "Share On VKontakte")[**](https://connect.ok.ru/offer?url=https://www.baeldung.com/java-testing-system-out-println "Share On Odnoklassniki")

**

Shouldn’t the tearDown method in section 3 be annotated with @AfterEach instead of @BeforeEach?

**

[![Loredana Crusoveanu](https://secure.gravatar.com/avatar/1493876cb13e5a64541736983032d2da?s=64&r=g)](https://www.baeldung.com/author/loredana-crusoveanu/)

[Loredana Crusoveanu](https://www.baeldung.com/author/loredana-crusoveanu/)

** 2 months ago

**

[**](https://twitter.com/intent/tweet?text=Good%20catch,%20Richard.%0AThanks,%20we’ve%20updated%20the%20section.%0A&url=https%3A%2F%2Fwww.baeldung.com%2Fjava-testing-system-out-println%23comment-13847 "Share On Twitter")[**](https://vk.com/share.php?url=https://www.baeldung.com/java-testing-system-out-println "Share On VKontakte")[**](https://connect.ok.ru/offer?url=https://www.baeldung.com/java-testing-system-out-println "Share On Odnoklassniki")

**

** Reply to  [Richard](#comment-13846)

Good catch, Richard.

Thanks, we’ve updated the section.

Comments are closed on this article!

[](https://www.ezoic.com/what-is-ezoic/)![](https://go.ezoic.net/utilcave_com/img/ezoic.png)report this ad

[](https://www.ezoic.com/what-is-ezoic/)![](https://go.ezoic.net/utilcave_com/img/ezoic.png)report this ad

[](https://www.ezoic.com/what-is-ezoic/)![](https://go.ezoic.net/utilcave_com/img/ezoic.png)report this ad

![The Baeldung logo](https://www.baeldung.com/wp-content/themes/baeldung/icon/logo.svg)

#### Categories

-   [Spring](https://www.baeldung.com/category/spring/)
-   [REST](https://www.baeldung.com/category/rest/)
-   [Java](https://www.baeldung.com/category/java/)
-   [Security](https://www.baeldung.com/category/security-2/)
-   [Persistence](https://www.baeldung.com/category/persistence/)
-   [Jackson](https://www.baeldung.com/category/json/jackson/)
-   [HTTP Client-Side](https://www.baeldung.com/category/http/)
-   [Kotlin](https://www.baeldung.com/category/kotlin/)

#### Series

-   [Java “Back to Basics” Tutorial](/java-tutorial)
-   [Jackson JSON Tutorial](/jackson)
-   [HttpClient 4 Tutorial](/httpclient-guide)
-   [REST with Spring Tutorial](/rest-with-spring-series)
-   [Spring Persistence Tutorial](/persistence-with-spring-series)
-   [Security with Spring](/security-spring)

#### About

-   [About Baeldung](/about)
-   [The Courses](https://courses.baeldung.com)
-   [Jobs](/tag/active-job/)
-   [The Full Archive](/full_archive)
-   [Editors](/editors)
-   [Our Partners](/partners)
-   [Advertise on Baeldung](/advertise)

-   [Terms of Service](/terms-of-service)
-   [Privacy Policy](/privacy-policy)
-   [Company Info](/baeldung-company-info)
-   [Contact](/contact)

![The Baeldung Logo](https://www.baeldung.com/wp-content/themes/baeldung/icon/whiteleaf.svg)

[wpDiscuz](javascript:void(0);)

Insert

x

x

![Quantcast](//pixel.quantserve.com/pixel/p-31iz6hfFutd16.gif?labels=Domain.baeldung_com,DomainId.120073)

![](https://sb.scorecardresearch.com/p?c1=2&c2=20015427&cv=2.0&cj=1)
