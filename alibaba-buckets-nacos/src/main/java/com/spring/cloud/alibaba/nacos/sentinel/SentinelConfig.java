package com.spring.cloud.alibaba.nacos.sentinel;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.csp.sentinel.slots.system.SystemRule;
import com.alibaba.csp.sentinel.slots.system.SystemRuleManager;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

//@Configuration
public class SentinelConfig {

    @ConditionalOnMissingBean
    public SentinelResourceAspect sentinelResourceAspect() {
        return new SentinelResourceAspect();
    }

    @PostConstruct
    private void initFlowRules() {
        List<FlowRule> rules = new ArrayList<>();

        //流控规则
        FlowRule rule = new FlowRule();
        rule.setResource("test");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule.setCount(1);
        rules.add(rule);
        FlowRuleManager.loadRules(rules);

        //降级规则，可以多个degradeRule rule
        //DegradeRuleManager.getRules()可以获取到已经设置的降级规则
        List<DegradeRule> degradeRules = new ArrayList<>();
        DegradeRule degradeRule = new DegradeRule();
        //设置资源名称，sentinel降级都是以资源为单位进行
        degradeRule.setResource("test");
        //使用异常统计降级,分钟统计,滑动时间窗口
        degradeRule.setGrade(RuleConstant.DEGRADE_GRADE_RT);
        //异常数达到的数量阈值
        degradeRule.setCount(2);
        //秒级时间窗口,该值必须有且必须大于零，否则降级将无法生效
        degradeRule.setTimeWindow(10);
        //最小请求数
        degradeRule.setMinRequestAmount(6);
        degradeRules.add(degradeRule);
        //重新加载限流规则，此处将覆盖原有的限流，所以如果想要不覆盖
        //请使用DegradeRuleManager.getRules()获取到的加入到rules中
        DegradeRuleManager.loadRules(degradeRules);

        // 系统规则
        List<SystemRule> systemRules = new ArrayList<>();
        SystemRule systemRule = new SystemRule();
        systemRule.setQps(0);
//        systemRule.setHighestSystemLoad(10);
        rules.add(rule);
        SystemRuleManager.loadRules(systemRules);

    }
}

