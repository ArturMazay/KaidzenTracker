package com.aistudio.kaidzentracker.android.screensimport android.widget.Toastimport androidx.compose.foundation.ExperimentalFoundationApiimport androidx.compose.foundation.backgroundimport androidx.compose.foundation.layout.Boximport androidx.compose.foundation.layout.fillMaxSizeimport androidx.compose.foundation.layout.paddingimport androidx.compose.foundation.lazy.LazyColumnimport androidx.compose.runtime.*import androidx.compose.runtime.livedata.observeAsStateimport androidx.compose.ui.Alignmentimport androidx.compose.ui.Modifierimport androidx.compose.ui.graphics.Colorimport androidx.compose.ui.platform.LocalContextimport androidx.compose.ui.unit.dpimport androidx.navigation.NavControllerimport com.aistudio.kaidzentracker.android.screens.commonScreenComponents.*import com.aistudio.kaidzentracker.android.theme.*import com.aistudio.kaidzentracker.android.ui.viewmodels.StandartWorkViewModelimport com.aistudio.kaidzentracker.android.ui.viewmodels.models.ApplicationIntentimport org.koin.androidx.compose.koinViewModelimport kotlin.math.floor@OptIn(ExperimentalFoundationApi::class)@Composablefun StandartWorkScreen(    modifier: Modifier,    navController: NavController,    standartWorkViewModel: StandartWorkViewModel = koinViewModel(),) {    val context = LocalContext.current    val state = standartWorkViewModel.standartWorkScreenState.observeAsState().value    var visible by remember { mutableStateOf(false) }    when (state!!.isLoading) {        false -> Box(modifier = modifier            .fillMaxSize()            .background(color = Color.White)) {            LazyColumn(modifier = modifier.padding(bottom = 45.dp)) {                item {                    CommonLogoAppBar(modifier = modifier, isAnimated = visible)                }                stickyHeader {                    CommonHeaderText(                        modifier = modifier,                        textHeader = "СТАНДАРТИЗИРОВАННАЯ РАБОТА",                        colorBackgraund = PrimaryVariant,                        height = 28,                        shape = 5,                        paddingHorizontal = 6                    )                }                item {                    CommonTableHeader(modifier = modifier)                }                item {                    CommonButtonsInput(modifier = modifier,                        value = state.firstQuestions.toInt(),                        onValueChange = { firstQuestions ->                            standartWorkViewModel.getIntent(intent = ApplicationIntent.ChangedFirstQuestions(                                newResult = firstQuestions))                        },                        numberQuestions = "1",                        textQuestions = "Процент стандартизированных процессов",                        textRecommendation = "1. Составить перечень приоритетных процессов,\n" +                                "наиболее сильно влияющих на ГПР, безопасность и качество работ.\n" +                                "2. Составить план-график стандартизации\n" +                                "приоритетных процессов 3. Регулярно проводить\n" +                                "аудит процесса стандартизации приоритетных процессов")                }                item {                    CommonButtonsInput(modifier = modifier,                        value = state.secondQuestions.toInt(),                        onValueChange = { secondQuestions ->                            standartWorkViewModel.getIntent(intent = ApplicationIntent.ChangedSecondQuestions(                                newResult = secondQuestions))                        },                        numberQuestions = "2",                        textQuestions = "Процент внедренных стандартов согласно \"Экрана стандартов\"",                        textRecommendation = "Регулярный аудит внедрения стандартов на проектах и в подразделениях")                }                item {                    CommonButtonsInput(modifier = modifier,                        value = state.threeQuestions.toInt(),                        onValueChange = { threeQuestions ->                            standartWorkViewModel.getIntent(intent = ApplicationIntent.ChangedThreeQuestions(                                newResult = threeQuestions))                        },                        numberQuestions = "3",                        textQuestions = "Процент персонала, выполняющего работу согласно Стандартов (выборочная проверка не менее 10 работников)",                        textRecommendation = "Регулярный аудит выполнения стандартов сотрудниками проектов и подразделений")                }                item {                    CommonButtonsInput(modifier = modifier,                        value = state.fourQuestions.toInt(),                        onValueChange = { fourQuestions ->                            standartWorkViewModel.getIntent(intent = ApplicationIntent.ChangedFourQuestions(                                newResult = fourQuestions))                        },                        numberQuestions = "4",                        textQuestions = "Процент процессов, которые разделены на технологические, вспомогательные и управленческие",                        textRecommendation = "В перечне процессов подразделения произведено разделение их на технологические/вспомогательные и управленческие")                }                item {                    CommonButtonsInput(modifier = modifier,                        value = state.fiveQuestions.toInt(),                        onValueChange = { fiveQuestions ->                            standartWorkViewModel.getIntent(intent = ApplicationIntent.ChangedFiveQuestions(                                newResult = fiveQuestions))                        },                        numberQuestions = "5",                        textQuestions = "Процент рабочих мест, на которых размещены в доступных зонах Стандарты",                        textRecommendation = "1. Разработать информационные стенды\n" +                                "\"Стандарты и Стандартные операционные процедуры\"\n" +                                "2. Разместить стандарты на рабочих местах в доступных зонах")                }                item {                    CommonButtonsInput(modifier = modifier,                        value = state.sixQuestions.toInt(),                        onValueChange = { sixQuestions ->                            standartWorkViewModel.getIntent(intent = ApplicationIntent.ChangedSixQuestions(                                newResult = sixQuestions))                        },                        numberQuestions = "6",                        textQuestions = "Процент процессов, для которых разработана процедура оперативного реагирования на отклонения.",                        textRecommendation = "Разработать процедуру оперативного реагирования на отклонения в процессах")                }                item {                    val result = floor(state.result * 10) / 10.0                    ResultView(modifier = modifier,                        textResult = result.toString(),                        text = "Итог")                }                item {                    val newResult = floor(state.newResult * 10) / 10.0                    ResultView(modifier = modifier,                        textResult = newResult.toString(),                        text = "Предыдущее значение")                }            }            CommonButtons(                modifier = modifier.align(Alignment.BottomCenter),                onClickSave = {                    Toast.makeText(context, "Сохранено", Toast.LENGTH_SHORT).show()                    standartWorkViewModel.getIntent(intent = ApplicationIntent.SaveClicked)                },                onClickDelete = {                    Toast.makeText(context, "Удалено", Toast.LENGTH_SHORT).show()                    standartWorkViewModel.getIntent(intent = ApplicationIntent.DeletedClicked)                },                onClickNext = { navController.navigate(route = "mappingScreen") },                onClickBack = { navController.popBackStack() }                ,navController = navController            )        }        true -> FullScreenLoaderComponent(modifier = modifier)    }    LaunchedEffect(key1 = Unit, block = {        standartWorkViewModel.getIntent(intent = ApplicationIntent.InitialScreen)        visible = true    })}