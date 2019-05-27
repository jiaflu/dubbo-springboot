package com.jiaflu.hello.dubbo.service.user.provider;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloDubboServiceUserProviderApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void test() {
        int[] nums = {1,2,3,4,5};
        System.out.println(binarySearch(nums, 3));
    }

    public static int binarySearch(int[] nums, int key) {
        int l = 0, h = nums.length - 1;
        while (l <= h) {
            int m = (l + h) / 2;
            if (nums[m] == key) {
                return m;
            } else if (nums[m] > key) {
                h = m - 1;
            } else {
                l = m + 1;
            }
        }
        return -1;
    }

}
