

Unit Testing of System.out.println() with JUnit
===============================================

by [Jonathan Cook](https://www.baeldung.com/author/jonathan-cook/ "Posts by Jonathan Cook")

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
