package com.epam.preprod.tymoshenko.task5;

import com.epam.preprod.tymoshenko.task5.part1.FileReaderTest;
import com.epam.preprod.tymoshenko.task5.part2.filter.impl.FilterByExtensionTest;
import com.epam.preprod.tymoshenko.task5.part2.filter.impl.FilterByModifiedDateTest;
import com.epam.preprod.tymoshenko.task5.part2.filter.impl.FilterByNameTest;
import com.epam.preprod.tymoshenko.task5.part2.filter.impl.FilterBySizeTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({FileReaderTest.class, FilterByExtensionTest.class,
        FilterByModifiedDateTest.class, FilterBySizeTest.class, FilterByNameTest.class})
public class AllTestsTask5 {
}
