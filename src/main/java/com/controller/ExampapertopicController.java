
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 试卷选题
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/exampapertopic")
public class ExampapertopicController {
    private static final Logger logger = LoggerFactory.getLogger(ExampapertopicController.class);

    private static final String TABLE_NAME = "exampapertopic";

    @Autowired
    private ExampapertopicService exampapertopicService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private DictionaryService dictionaryService;//字典
    @Autowired
    private ExampaperService exampaperService;//试卷表
    @Autowired
    private ExamquestionService examquestionService;//试题表
    @Autowired
    private ExamrecordService examrecordService;//答题记录表
    @Autowired
    private ExamredetailsService examredetailsService;//答题详情表
    @Autowired
    private ForumService forumService;//论坛
    @Autowired
    private NewsService newsService;//公告信息
    @Autowired
    private XuexishipinService xuexishipinService;//课程学习
    @Autowired
    private XuexishipinCollectionService xuexishipinCollectionService;//课程收藏
    @Autowired
    private YonghuService yonghuService;//用户
    @Autowired
    private ZhiyejieshaoService zhiyejieshaoService;//职业介绍
    @Autowired
    private ZhiyejieshaoCollectionService zhiyejieshaoCollectionService;//职业介绍收藏
    @Autowired
    private UsersService usersService;//管理员


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        CommonUtil.checkMap(params);
        PageUtils page = exampapertopicService.queryPage(params);

        //字典表数据转换
        List<ExampapertopicView> list =(List<ExampapertopicView>)page.getList();
        for(ExampapertopicView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        ExampapertopicEntity exampapertopic = exampapertopicService.selectById(id);
        if(exampapertopic !=null){
            //entity转view
            ExampapertopicView view = new ExampapertopicView();
            BeanUtils.copyProperties( exampapertopic , view );//把实体数据重构到view中
            //级联表 试题表
            //级联表
            ExamquestionEntity examquestion = examquestionService.selectById(exampapertopic.getExamquestionId());
            if(examquestion != null){
            BeanUtils.copyProperties( examquestion , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setExamquestionId(examquestion.getId());
            }
            //级联表 试卷表
            //级联表
            ExampaperEntity exampaper = exampaperService.selectById(exampapertopic.getExampaperId());
            if(exampaper != null){
            BeanUtils.copyProperties( exampaper , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setExampaperId(exampaper.getId());
            }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody ExampapertopicEntity exampapertopic, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,exampapertopic:{}",this.getClass().getName(),exampapertopic.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<ExampapertopicEntity> queryWrapper = new EntityWrapper<ExampapertopicEntity>()
            .eq("exampaper_id", exampapertopic.getExampaperId())
            .eq("examquestion_id", exampapertopic.getExamquestionId())
            .eq("exampapertopic_number", exampapertopic.getExampapertopicNumber())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ExampapertopicEntity exampapertopicEntity = exampapertopicService.selectOne(queryWrapper);
        if(exampapertopicEntity==null){
            exampapertopic.setCreateTime(new Date());
            exampapertopicService.insert(exampapertopic);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody ExampapertopicEntity exampapertopic, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,exampapertopic:{}",this.getClass().getName(),exampapertopic.toString());
        ExampapertopicEntity oldExampapertopicEntity = exampapertopicService.selectById(exampapertopic.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");

            exampapertopicService.updateById(exampapertopic);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<ExampapertopicEntity> oldExampapertopicList =exampapertopicService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        exampapertopicService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            List<ExampapertopicEntity> exampapertopicList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            ExampapertopicEntity exampapertopicEntity = new ExampapertopicEntity();
//                            exampapertopicEntity.setExampaperId(Integer.valueOf(data.get(0)));   //试卷 要改的
//                            exampapertopicEntity.setExamquestionId(Integer.valueOf(data.get(0)));   //试题 要改的
//                            exampapertopicEntity.setExampapertopicNumber(Integer.valueOf(data.get(0)));   //试题分数 要改的
//                            exampapertopicEntity.setCreateTime(date);//时间
                            exampapertopicList.add(exampapertopicEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        exampapertopicService.insertBatch(exampapertopicList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }




    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = exampapertopicService.queryPage(params);

        //字典表数据转换
        List<ExampapertopicView> list =(List<ExampapertopicView>)page.getList();
        for(ExampapertopicView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        ExampapertopicEntity exampapertopic = exampapertopicService.selectById(id);
            if(exampapertopic !=null){


                //entity转view
                ExampapertopicView view = new ExampapertopicView();
                BeanUtils.copyProperties( exampapertopic , view );//把实体数据重构到view中

                //级联表
                    ExamquestionEntity examquestion = examquestionService.selectById(exampapertopic.getExamquestionId());
                if(examquestion != null){
                    BeanUtils.copyProperties( examquestion , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setExamquestionId(examquestion.getId());
                }
                //级联表
                    ExampaperEntity exampaper = exampaperService.selectById(exampapertopic.getExampaperId());
                if(exampaper != null){
                    BeanUtils.copyProperties( exampaper , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setExampaperId(exampaper.getId());
                }
                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody ExampapertopicEntity exampapertopic, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,exampapertopic:{}",this.getClass().getName(),exampapertopic.toString());
        Wrapper<ExampapertopicEntity> queryWrapper = new EntityWrapper<ExampapertopicEntity>()
            .eq("exampaper_id", exampapertopic.getExampaperId())
            .eq("examquestion_id", exampapertopic.getExamquestionId())
            .eq("exampapertopic_number", exampapertopic.getExampapertopicNumber())
//            .notIn("exampapertopic_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ExampapertopicEntity exampapertopicEntity = exampapertopicService.selectOne(queryWrapper);
        if(exampapertopicEntity==null){
            exampapertopic.setCreateTime(new Date());
        exampapertopicService.insert(exampapertopic);

            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }


    /**
    * 后端修改
    */
    @RequestMapping("/examinationUpdate")
    public R examinationUpdate(@RequestBody ExampapertopicEntity exampapertopic, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,exampapertopic:{}",this.getClass().getName(),exampapertopic.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));

        //根据字段查询是否有相同数据
        Wrapper<ExampapertopicEntity> queryWrapper = new EntityWrapper<ExampapertopicEntity>()
            .notIn("id",exampapertopic.getId())
            .andNew()
            .eq("exampaper_id", exampapertopic.getExampaperId())
            .eq("examquestion_id", exampapertopic.getExamquestionId())
            .eq("exampapertopic_number", exampapertopic.getExampapertopicNumber())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ExampapertopicEntity exampapertopicEntity = exampapertopicService.selectOne(queryWrapper);
        if(exampapertopicEntity==null){
            ExampapertopicEntity exampapertopicEntity1 = exampapertopicService.selectById(exampapertopic.getId());
            if(exampapertopic.getExampaperId() == null){
              return R.error("修改时数据未获取到");
            }
            if(exampapertopicEntity1.getExampapertopicNumber()!=exampapertopic.getExampapertopicNumber()){
                ExampaperEntity exampaperEntity = exampaperService.selectById(exampapertopic.getExampaperId());
                exampaperEntity.setExampaperMyscore((exampaperEntity.getExampaperMyscore()-exampapertopicEntity1.getExampapertopicNumber())+exampapertopic.getExampapertopicNumber());
                exampaperService.updateById(exampaperEntity);
            }
            exampapertopicService.updateById(exampapertopic);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 删除
    */
    @RequestMapping("/examinationDelete")
    public R examinationDelete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<ExampapertopicEntity> exampapertopicEntities = exampapertopicService.selectBatchIds(Arrays.asList(ids));
        ExampaperEntity exampaperEntity1 = exampaperService.selectById(exampapertopicEntities.get(0).getExampaperId());
        for (ExampapertopicEntity topic:exampapertopicEntities) {
            exampaperEntity1.setExampaperMyscore(exampaperEntity1.getExampaperMyscore()-topic.getExampapertopicNumber());
        }
        exampaperService.updateById(exampaperEntity1);
        exampapertopicService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    /**
    * 自动组卷
    */
    @RequestMapping("/zidongzujuan")
    public R zidongzujuan(@RequestParam Map<String, Object> params, HttpServletRequest request){
        //试卷科目
        Integer kemuTypes = Integer.valueOf(String.valueOf(params.get("kemuTypes")));
        //试卷数据信息
        ExampaperEntity exampaperEntity = new ExampaperEntity();
        exampaperEntity.setId(Integer.valueOf(String.valueOf(params.get("exampaperId"))));
        exampaperEntity.setExampaperMyscore(0);
        //单选数量和分数
        Integer danNum = Integer.valueOf(String.valueOf(params.get("danNum")));
        Integer danFen = Integer.valueOf(String.valueOf(params.get("danFen")));
        ArrayList<ExamquestionEntity> danList = new ArrayList<>();
        //多选数量和分数
        Integer duoNum = Integer.valueOf(String.valueOf(params.get("duoNum")));
        Integer duoFen = Integer.valueOf(String.valueOf(params.get("duoFen")));
        ArrayList<ExamquestionEntity> duoList = new ArrayList<>();
        //判断数量和分数
        Integer panNum = Integer.valueOf(String.valueOf(params.get("panNum")));
        Integer panFen = Integer.valueOf(String.valueOf(params.get("panFen")));
        ArrayList<ExamquestionEntity> panList = new ArrayList<>();
        //填空数量和分数
        Integer tianNum = Integer.valueOf(String.valueOf(params.get("tianNum")));
        Integer tianFen = Integer.valueOf(String.valueOf(params.get("tianFen")));
        ArrayList<ExamquestionEntity> tianList = new ArrayList<>();
        //存储已经筛选的数据
        ArrayList<ExampapertopicEntity> exampapertopicEntities = new ArrayList<>();
        //随机数
        Random random = new Random();

        //查询全部试题信息
        Wrapper<ExamquestionEntity> queryWrapper = new EntityWrapper<ExamquestionEntity>().eq("kemu_types", kemuTypes);
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        List<ExamquestionEntity> exampapertopicLists = examquestionService.selectList(null);
        for (ExamquestionEntity examquestion: exampapertopicLists) {
            //将对应类型数据放入对应的list集合中
            if(examquestion.getExamquestionTypes() == 1){//单选题
                danList.add(examquestion);
            }else if(examquestion.getExamquestionTypes() == 2){//多选题
                duoList.add(examquestion);
            }else if(examquestion.getExamquestionTypes() == 3){//判断题
                panList.add(examquestion);
            }else{//填空题
                tianList.add(examquestion);
            }
        }

        //判断前台传来的题数值是否为空
        if(danNum!=null && danNum>0 && danFen != null && danFen >0){//单选
            if(danList.size()<danNum)
                return R.error("单选题数量"+danNum+"超过数据库中存在的最大数量"+danList.size());
            for (int i = 0 ;i<danNum;i++){
                ExampapertopicEntity exampapertopicEntity = new ExampapertopicEntity();
                int intRandom = random.nextInt(danList.size());
                exampaperEntity.setExampaperMyscore(exampaperEntity.getExampaperMyscore()+danFen);
                exampapertopicEntity.setExamquestionId(danList.get(intRandom).getId());
                exampapertopicEntity.setExampapertopicNumber(danFen);
                exampapertopicEntity.setExampaperId(exampaperEntity.getId());
                exampapertopicEntities.add(exampapertopicEntity);
                danList.remove(intRandom);
            }
        }
        if(duoNum!=null && duoNum>0 && duoFen != null && duoFen >0){//多选
            if(duoList.size()<duoNum)
                return R.error("多选题数量"+duoNum+"超过数据库中存在的最大数量"+duoList.size());
            for (int i = 0 ;i<duoNum;i++){
                ExampapertopicEntity exampapertopicEntity = new ExampapertopicEntity();
                int intRandom = random.nextInt(duoList.size() );
                exampaperEntity.setExampaperMyscore(exampaperEntity.getExampaperMyscore()+duoFen);
                exampapertopicEntity.setExamquestionId(duoList.get(intRandom).getId());
                exampapertopicEntity.setExampapertopicNumber(duoFen);
                exampapertopicEntity.setExampaperId(exampaperEntity.getId());
                exampapertopicEntities.add(exampapertopicEntity);
                duoList.remove(intRandom);
            }
        }
        if(panNum!=null && panNum>0 && panFen != null && panFen >0){//判断
            if(panList.size()<panNum)
                return R.error("判断题数量"+panNum+"超过数据库中存在的最大数量"+panList.size());
            for (int i = 0 ;i<panNum;i++){
                ExampapertopicEntity exampapertopicEntity = new ExampapertopicEntity();
                int intRandom = random.nextInt(panList.size());
                exampaperEntity.setExampaperMyscore(exampaperEntity.getExampaperMyscore()+panFen);
                exampapertopicEntity.setExamquestionId(panList.get(intRandom).getId());
                exampapertopicEntity.setExampapertopicNumber(panFen);
                exampapertopicEntity.setExampaperId(exampaperEntity.getId());
                exampapertopicEntities.add(exampapertopicEntity);
                panList.remove(intRandom);
            }
        }
        if(tianNum!=null && tianNum>0 && tianFen != null && tianFen >0){//填空
            if(tianList.size()<tianNum)
                return R.error("填空题数量"+tianNum+"超过数据库中存在的最大数量"+tianList.size());
            for (int i = 0 ;i<tianNum;i++){
                ExampapertopicEntity exampapertopicEntity = new ExampapertopicEntity();
                int intRandom = random.nextInt(tianList.size());
                exampaperEntity.setExampaperMyscore(exampaperEntity.getExampaperMyscore()+tianFen);
                exampapertopicEntity.setExamquestionId(tianList.get(intRandom).getId());
                exampapertopicEntity.setExampapertopicNumber(tianFen);
                exampapertopicEntity.setExampaperId(exampaperEntity.getId());
                exampapertopicEntities.add(exampapertopicEntity);
                tianList.remove(intRandom);
            }
        }

        exampaperService.updateById(exampaperEntity);
        exampapertopicService.insertBatch(exampapertopicEntities);
        return R.ok();
    }

    /**
    * 获取当前试卷的总分数
    */
    @RequestMapping("/sumExampapertopicNumber")
        public R sumExampapertopicNumber(Integer exampaperId, HttpServletRequest request){
        if(exampaperId==null || exampaperId<=0)
            return R.error("试卷id不能为空");
        List<ExampapertopicEntity> list = exampapertopicService.selectList(
            new EntityWrapper<ExampapertopicEntity>()
                .eq("exampaper_id", exampaperId)
        );
        Integer exampapertopicNumber=0;
        if(!(list == null || list.size()==0)){
            for(ExampapertopicEntity exampapertopicEntity:list){
                exampapertopicNumber=exampapertopicNumber+exampapertopicEntity.getExampapertopicNumber();
            }
        }
        return R.ok().put("exampapertopicNumber",exampapertopicNumber);
    }

    /**
     * 答题题目获取
     */
    @RequestMapping("/questionAcquisition")
    public R questionAcquisition(Integer exampaperId, HttpServletRequest request){
        //获取当前试卷的选题信息
        HashMap<String, Object> params = new HashMap<>();
        params.put("limit","9999");
        params.put("exampaperId", exampaperId);
        CommonUtil.checkMap(params);
        PageUtils page = exampapertopicService.queryPage(params);
        List<ExampapertopicView> exampapertopicView =(List<ExampapertopicView>)page.getList();

        //试题id存放list
        List<Integer> exampapertopicId = new ArrayList<>();
        //循环选题信息获取试题id并存放起来
        for (ExampapertopicEntity papertopic:exampapertopicView) {
            exampapertopicId.add(papertopic.getExamquestionId());
        }
        //查询试题数据
        List<ExamquestionEntity> examquestionEntity = examquestionService.selectBatchIds(exampapertopicId);
        //试题数据存放到选题view中
        examquestionEntity.forEach(item -> {
            BeanUtils.copyProperties(item, exampapertopicView);
        });
        //字典表数据转换
        for(ExampapertopicView c:exampapertopicView){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data",exampapertopicView);
    }

    /**
     * 提交试卷
     */
    @RequestMapping("/submitQuestions")
    public R submitQuestions(@RequestParam Map<String, Object> params, HttpServletRequest request){
        //答题编号
        String uuid = String.valueOf(new Date().getTime());
        //答题用户
        Integer yonghuId = Integer.valueOf(String.valueOf(params.get("yonghuId")));
        //试卷id
        Integer exampaperId = Integer.valueOf(String.valueOf(params.get("exampaperId")));
        //答题记录表信息
        ExamrecordEntity examrecordEntity = new ExamrecordEntity();
        examrecordEntity.setExamrecordUuidNumber(uuid);
        examrecordEntity.setTotalScore(0);//总分数
        examrecordEntity.setExampaperId(exampaperId);//试卷id
        examrecordEntity.setYonghuId(yonghuId);//答题用户
        examrecordEntity.setInsertTime(new Date());//时间
        examrecordEntity.setCreateTime(new Date());//时间
        //答题信息
        List<Map<String,String>> answerList = (List<Map<String,String>>)JSON.parse(String.valueOf(params.get("answerList")));
        //答题map
        HashMap<String, String> answerMap = new HashMap<>();
        for (Map<String,String> map:answerList) {
            answerMap.put(map.get("examquestionId"),map.get("answer"));
        }
        //试题信息
        HashMap<String, Object> map = new HashMap<>();
        map.put("limit","9999");
        map.put("exampaperId", exampaperId);
        CommonUtil.checkMap(map);
        PageUtils page = exampapertopicService.queryPage(map);
        List<ExampapertopicView> exampapertopicList =(List<ExampapertopicView>)page.getList();


        //答题详情
        List<ExamredetailsEntity> examredetailsList = new ArrayList<>();

        if(exampapertopicList != null && exampapertopicList.size()>0){
            boolean insert = examrecordService.insert(examrecordEntity);
            if(!insert){
                return R.error();
            }
        }
        //判断用户的答案
        for (ExampapertopicView exampapertopic:exampapertopicList) {
            boolean cuoti = false;
            //答题信息
            ExamredetailsEntity examredetailsEntity = new ExamredetailsEntity();
                //答题数据补充
                examredetailsEntity.setExamredetailsUuidNumber(uuid);
                examredetailsEntity.setYonghuId(yonghuId);
                examredetailsEntity.setExamquestionId(exampapertopic.getExamquestionId());
                examredetailsEntity.setCreateTime(new Date());
            //判断是否答题
            if(StringUtil.isNotEmpty(answerMap.get(exampapertopic.getExamquestionId()))){
                examredetailsEntity.setExamredetailsMyanswer(answerMap.get(exampapertopic.getExamquestionId()));
                //多选题判断对错
                if(exampapertopic.getExamquestionTypes() == 2){
                    //正确答案
                    List<String> split = Arrays.asList(exampapertopic.getExamquestionAnswer().split(","));
                    ArrayList<String> splitLs = new ArrayList<>();
                    splitLs.addAll(split);
                    //用户答案
                    List<String> answer = Arrays.asList(answerMap.get(exampapertopic.getExamquestionId()).split(","));
                    List<String> answerLs = new ArrayList<>();
                    answerLs.addAll(answer);
                    //判断用户是否回答正确
                    if(splitLs.size() == answerLs.size()){//判断用户回答信息的长度是否正确答案的长度一样
                        splitLs.retainAll(answerLs);
                        if(splitLs.size() == answerLs.size()){//判断内容是否正确
                            examrecordEntity.setTotalScore(examrecordEntity.getTotalScore()+exampapertopic.getExampapertopicNumber());
                            examredetailsEntity.setExamredetailsMyscore(exampapertopic.getExampapertopicNumber());
                        }else{//错误
                            cuoti = true;
                            examredetailsEntity.setExamredetailsMyscore(0);
                        }
                    }else{//错误
                        cuoti = true;
                        examredetailsEntity.setExamredetailsMyscore(0);
                    }
                }else{
                    //其他题判断对错
                    if(answerMap.get(exampapertopic.getExamquestionId()).equals(exampapertopic.getExamquestionAnswer())){//正确
                        examredetailsEntity.setExamredetailsMyscore(exampapertopic.getExampapertopicNumber());
                        examrecordEntity.setTotalScore(examrecordEntity.getTotalScore()+exampapertopic.getExampapertopicNumber());
                    }else{//错误
                        cuoti = true;
                        examredetailsEntity.setExamredetailsMyscore(0);
                    }
                }
            }else{//未作答
                cuoti = true;
                examredetailsEntity.setExamredetailsMyanswer("未作答");
                examredetailsEntity.setExamredetailsMyscore(0);
            }
            if(cuoti){
                cuoti = false;
            }
            examredetailsList.add(examredetailsEntity);
        }

        examredetailsService.insertBatch(examredetailsList);
        examrecordService.updateById(examrecordEntity);
        return R.ok();
    }
}

