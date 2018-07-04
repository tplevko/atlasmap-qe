@Mappings
@Combine
Feature: atlasmap is able to combine multiple inputs into one filed

  Background:
    Given atlasmap is clean
    And atlasmap contains TestClass
    And browser is opened
    And internal mapping is set to "false"


  Scenario: Simple combine workflow, executed from Mapping Details window
    When click on "sourceString"
    And add select "Combine" action
    And for "input-target-" id input set "targetCombineString"

    And add click "Add Source" button
    And for "input-source-" id input set "sourceAnotherString"

    And set source data
      | sourceString | sourceAnotherString |
      | foo          | bar                 |

    And set expected data
      | targetCombineString |
      | foo bar             |
    Then  save and verify mapping as "SimpleCombine.xml"


  Scenario: Mixed types combine , executed from Mapping Details window
    When click on "sourceString"
    And add select "Combine" action
    When select "Space [ ]" separator
    And for "input-target-" id input set "targetCombineString"


    And add "/sourceChar" to combine
    And add "/sourceInteger" to combine
    And add "/sourceFloat" to combine
    And add "/sourceLong" to combine
    And add "/sourceShort" to combine
    And add "/sourceDouble" to combine
    And add "/sourceDate" to combine

    And add click "Add Transformation" link
    And select "Prepend" transformation
    And for "input-string" input set "Combined: "

    Then save and verify combine mapping with " " separator as "ComplexCombine.xml"

    When select "Colon [:]" separator
    Then save and verify combine mapping with ":" separator as "ComplexCombineColon.xml"

    When select "Ampersand [&]" separator
    Then save and verify combine mapping with "&" separator as "ComplexCombineAmpersand.xml"

    When select "At Sign [@]" separator
    Then save and verify combine mapping with "@" separator as "ComplexCombineAtSign.xml"

    When select "Backslash [\]" separator
    Then save and verify combine mapping with "\" separator as "ComplexCombineBackslash.xml"

    When select "Comma [,]" separator
    Then save and verify combine mapping with "," separator as "ComplexCombineComma.xml"

    When select "Dash [-]" separator
    Then save and verify combine mapping with "-" separator as "ComplexCombineDash.xml"

    When select "Equal [=]" separator
    Then save and verify combine mapping with "=" separator as "ComplexCombineEqual.xml"

    When select "Hash [#]" separator
    Then save and verify combine mapping with "#" separator as "ComplexCombineHash.xml"

    When select "Period [.]" separator
    Then save and verify combine mapping with "." separator as "ComplexCombinePeriod.xml"

    When select "Pipe [|]" separator
    Then save and verify combine mapping with "|" separator as "ComplexCombinePipe.xml"

    When select "Semicolon [;]" separator
    Then save and verify combine mapping with ";" separator as "ComplexCombineSemicolon.xml"

    When select "Slash [/]" separator
    Then save and verify combine mapping with "/" separator as "ComplexCombineSlash.xml"

    When select "Underscore [_]" separator
    Then save and verify combine mapping with "_" separator as "ComplexCombinePipe.xml"


  Scenario: Mixed types combine with mixed indexes , executed from Mapping Details window
    When click on "sourceString"
    And add select "Combine" action
    And for "input-target-" id input set "/targetCombineString"

    And add "/sourceDouble" to combine
    And add "/sourceInteger" to combine
    And add "/sourceFloat" to combine
    And add "/sourceLong" to combine
    And add "/sourceShort" to combine
    And add "/sourceChar" to combine
    And add "/sourceDate" to combine


    And for "input-source-sourceShort" id input with "6" set "3"
    And for "input-source-sourceLong" id input with "6" set "4"
    And for "input-source-sourceFloat" id input with "6" set "5"



    And add click "Add Transformation" link
    And select "Prepend" transformation
    And for "input-string" input set "Combined: "
    And set source data
      | sourceString | sourceChar | sourceInteger | sourceFloat | sourceLong | sourceShort | sourceDouble |
      | numbers:     | 1          | 2             | 3           | 4          | 5           | 6            |

    And set expected data
      | targetCombineString                                     |
      | Combined: numbers: 6.0 5 4 3.0 2 1 1970-01-01T00:00:00Z |

    Then save and verify mapping as "ComplexCombineMixedIndexes.xml"



#  Scenario: Mixing indexes with drag'n'drop
#    When click on "sourceString"
#    And add select "Combine" action
#    And for "input-target-" id input set "/targetCombineString"
#
#    And add "/sourceDouble" to combine
#    And add "/sourceInteger" to combine
#    And add "/sourceFloat" to combine
#    And add "/sourceLong" to combine
#    And add "/sourceShort" to combine
#    And add "/sourceChar" to combine
#    And add "/sourceDate" to combine
#
#
#    #And for "input-source-sourceShort" id input with "6" set "3"
#    And drag "input-source-sourceShort" and drop on "input-source-sourceInteger"
#   # And for "input-source-sourceLong" id input with "6" set "4"
#   # And for "input-source-sourceFloat" id input with "6" set "5"


#
#    And add click "Add Transformation" link
#    And select "Prepend" transformation
#    And for "input-string" input set "Combined: "
#    And set source data
#      | sourceString | sourceChar | sourceInteger | sourceFloat | sourceLong | sourceShort | sourceDouble |
#      | numbers:     | 1          | 2             | 3           | 4          | 5           | 6            |
#
#    And set expected data
#      | targetCombineString                                     |
#      | Combined: numbers: 6.0 5 4 3.0 2 1 1970-01-01T00:00:00Z |

@SmokeTest
  Scenario: Simple combine with holding CMD/control button
    When click on "sourceString" holding cmd button
    And click on "sourceChar" holding cmd button
    And click on "sourceInteger" holding cmd button
    And click on "sourceFloat" holding cmd button
    And click on "sourceLong" holding cmd button
    And click on "sourceShort" holding cmd button
    And click on "sourceDouble" holding cmd button
    And click on "sourceDate" holding cmd button

    And click on "targetCombineString"

    And add click "Add Transformation" link
    And select "Prepend" transformation
    And for "input-string" input set "Combined: "

    Then save and verify combine mapping with " " separator as "ComplexCombineCMD.xml"


  Scenario: Gaps testing
    And click on "sourceInteger" holding cmd button
    And click on "sourceFloat" holding cmd button
    And click on "sourceLong" holding cmd button
    And click on "sourceDouble" holding cmd button
    And click on "targetCombineString"

    And for "input-source-sourceDouble" id input with "4" set "8"
    And for "input-source-sourceLong" id input with "3" set "6"
    And for "input-source-sourceFloat" id input with "2" set "4"
    And for "input-source-sourceInteger" id input with "1" set "2"

    And select "Colon [:]" separator
    And set source data
      | sourceInteger | sourceFloat | sourceLong | sourceDouble |
      | 2             | 4           | 6          | 8            |
    And set expected data
      | targetCombineString |
      | :2::4.0::6::8.0     |

    Then save and verify mapping as "ComplexCombineGaps.xml"
