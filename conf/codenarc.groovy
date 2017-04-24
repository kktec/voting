ruleset {
    ruleset('rulesets/basic.xml')
    ruleset('rulesets/braces.xml')
    ruleset('rulesets/concurrency.xml')
    ruleset('rulesets/convention.xml') {
        exclude 'NoDef'
        exclude 'TrailingComma'
    }
    ruleset('rulesets/design.xml')
    ruleset('rulesets/dry.xml') {
        exclude 'DuplicateNumberLiteral'
        exclude 'DuplicateMapLiteral'
        exclude 'DuplicateStringLiteral'
    }
    ruleset('rulesets/enhanced.xml')
    ruleset('rulesets/exceptions.xml')
    ruleset('rulesets/formatting.xml') {
        exclude 'ClassJavadoc'
        exclude 'ConsecutiveBlankLines'
        exclude 'TrailingWhitespace'
        SpaceAroundMapEntryColon {
            characterAfterColonRegex = /\s/
        }
    }
    ruleset('rulesets/generic.xml')
    ruleset('rulesets/groovyism.xml')
    ruleset('rulesets/imports.xml') {
        exclude 'NoWildcardImports'
    }
    ruleset('rulesets/jdbc.xml')
    ruleset('rulesets/junit.xml')
    ruleset('rulesets/logging.xml')
    ruleset('rulesets/naming.xml') {
        MethodName { regex = /[a-z|A-Z][\w\s'\(\)#:]*/ }
        FactoryMethodName { regex = /(build.*|make.*)/ }
    }
    ruleset('rulesets/security.xml') {
        exclude 'JavaIoPackageAccess'
    }
    ruleset('rulesets/serialization.xml')
    ruleset('rulesets/size.xml') {
        MethodCount { maxMethods = 40 }
    }
    ruleset('rulesets/unnecessary.xml') {
        exclude 'UnnecessaryBooleanExpression'
    }
    ruleset('rulesets/unused.xml')
}
