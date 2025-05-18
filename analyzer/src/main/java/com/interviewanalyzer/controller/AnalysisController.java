package com.interviewanalyzer.controller;

import com.interviewanalyzer.dto.ContentForm;
import com.interviewanalyzer.dto.Result;
import com.interviewanalyzer.service.AnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class AnalysisController {
    private final AnalysisService analysisService;

    @Autowired
    public AnalysisController(AnalysisService analysisService){
        this.analysisService = analysisService;
    }

    @GetMapping("/home/analysis")
    public String analysis(){
        return "analysis";
    }

    @GetMapping("/home/analysis/result")
    public String redirectResult(){
        return "result";
    }

    @PostMapping("/home/analysis/result")
    public String result(ContentForm form, RedirectAttributes redirectAttributes) throws IOException, InterruptedException {
        Result result = analysisService.analyze(form.getContent());

        redirectAttributes.addFlashAttribute("strengths", result.getStrengths());
        redirectAttributes.addFlashAttribute("weaknesses", result.getWeaknesses());
        redirectAttributes.addFlashAttribute("recap", result.getRecap());

        return "redirect:/home/analysis/result";
    }
}

// test form
// 안녕하십니까. 맡은 일에 책임을 다하고 꾸준히 발전하는 자세를 지닌 지원자입니다.저는 주어진 업무를 단순히 수행하는 데 그치지 않고, 더 나은 방법을 고민하며 효율을 높이는 데 집중해 왔습니다. 다양한 협업 경험을 통해 원활한 소통의 중요성을 깊이 깨달았고, 갈등 상황에서도 문제의 본질을 파악하고 해결 방안을 도출하는 능력을 키웠습니다. 또한 변화에 유연하게 대응하며, 새로운 기술이나 지식을 익히는 것을 즐깁니다. 귀사에서 제 역량을 발휘하고, 함께 성장할 수 있는 기회를 갖게 되기를 진심으로 희망합니다.