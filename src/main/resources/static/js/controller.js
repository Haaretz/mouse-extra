/**
 * Created by oleg.yakimchuk on 26/02/2017.
 */
'use strict';

var app=angular.module("vk", ['ui.bootstrap', 'ngAnimate'])
    .controller('SearchController', ["$scope", "$http", function ($scope, $http) {
    $scope.search = {};
    $scope.filteredItems= undefined;
    /* $scope.$watch('searchText', function (val) {
     var payload = { 'pollDescription': val };
     if (val != '' && val != undefined && val.length > 1) {
     Search.get(payload, function (data) {
     $scope.ByName = data;
     });
     } else {
     $scope.ByName = [];
     }
     });
     $scope.$watch('searchID', function (val) {
     var payload = { 'pollContentId': val };
     if (val != '' && val != undefined && val.length > 1) {
     Search.get(payload, function (data) {
     $scope.ByID = data;
     });
     } else {
     $scope.ByID = [];
     }
     });*/
    $scope.stepToWiners;
    $scope.winers;
    $scope.setStepToWin = function (action,id) {
        id= parseInt(id);
        var checked=action.currentTarget['checked'];
        $scope.stepToWiners=[];
        if(count!==undefined  && typeof(count) ==="number") {
            if ($scope.filteredItems !== undefined) {
                for (var i = 0, len = count; i < len; i++) {
                    var ranItem = 0;
                    ranItem = Math.floor(Math.random() * $scope.filteredItems.length);
                    if ($scope.filteredItems[ranItem].isWin == 0) {
                        $scope.filteredItems[ranItem].isWin = 1;
                        var pollId = $scope.filteredItems[ranItem].sub_id;
                        var remoteService = 'http://localhost:8080/extra/submissions/mark-win/';
                        $scope.winers.push($scope.filteredItems[ranItem]);
                        $scope.setWin(remoteService,pollId);
                    }
                }

            }
        }
    };
    $scope.setWin=function(url,id){
        $http({
            method: 'GET',
            url: url+id,
            cache: false
        }).then(function (response) {
                console.log("status:" + response.status);
        }).catch(function (response) {
                console.error('Error occurred:', response.status, response.data);
        }).finally(function () {
                console.log("Task Finished."+url+id);
         });
    };
    $scope.setWinCount = function (count) {
        count= parseInt(count);
       return count;
    };
    $scope.submitData = function (search, resultVarName) {
        if (search !== undefined && search.searchID !== undefined && search.searchID.pollContentId !== undefined) {
            $scope.config = search.searchID.pollContentId;
        }
        if (search !== undefined && search.searchText !== undefined && search.searchText.pollContentId !== undefined) {
            $scope.config = search.searchText.pollContentId;
        }
        // $http.post("http://localhost:46836/GetResponse.ashx", null, config)
        //  .success(function (data, status, header, config) {
        //      $scope[resultVarName] = data;
        //  }).error(function (data, status, header, config) {
        //      $scope[resultVarName] = "SUBMIT ERROR";
        //  });
        $http.get('/extra/report/' + $scope.config).then(function (response) {
            $scope.responses = response.data;
            $scope.QuestionName = $scope.responses[0].question;
            $scope.correctAnswer = $scope.responses[0].correctAnswer;
            $scope.CountCorrectAnswers = 0;
            $scope.CountUsers = $scope.responses.length;
            angular.forEach($scope.responses, function (value, key) {
                if (value.answerId == value.correctAnswer)
                    $scope.CountCorrectAnswers = $scope.CountCorrectAnswers + 1;
            });
            console.log("status:" + response.status);
        }).catch(function (response) {
            console.error('Error occurred:', response.status, response.data);
        }).finally(function () {
            console.log("Task Finished.");
        });
    };
    $http.get('/extra/polls/getall').then(function (response) {
        $scope.questions = response.data;
        console.log("status:" + response.status);
    }).catch(function (response) {
        console.log('Error occurred:', response.status, response.data);
    }).finally(function () {
        console.log("Task Finished.");
    });
    $http.get("Areas.xml",
        {
            transformResponse: function (cnv) {
                var x2js = new X2JS();
                var aftCnv = x2js.xml_str2json(cnv);
                console.log(aftCnv);
                return aftCnv;
            }
        })
        .then(function (response) {
            $scope.areas = response.data.Areas.Area;
            $scope.areaSelected = $scope.areas[0];
            console.log(response.data);
        });
    $scope.filter = {};
    $scope.makeChanged = function (selectedMakeCode) {
        if (selectedMakeCode._id !=-1) {
            $scope.cities = $scope.areas[selectedMakeCode._id].City;
            $scope.citySelected = $scope.cities[0];
        } else {
            $scope.cities = null;
        }
    };
    $scope.select = function (select) {
        if(select.cAnswer!==undefined){
            $scope.filter = {'answerId':select.cAnswer};
        }
        else if(select.cNoWin!==undefined) {
            $scope.filter = {'isWin': select.cNoWin};
        }
        else if (select !== undefined && select.__text!=undefined) {
            $scope.filter = { 'addressLineCity': select.__text };
        }
        else if (select !== undefined && select.count!=undefined && select.arr!==undefined) {
                var count = parseInt(select.count);
                var temp = [];
                if (count !== undefined && typeof(count) === "number") {
                    if (select.arr !== undefined) {
                        for (var i = 0, len = count; i < len; i++) {
                            var ranItem = 0;
                            ranItem = Math.floor(Math.random() * (1+select.arr.length-1));
                            if(select.arr[ranItem]!==undefined) {
                                temp.push(select.arr[ranItem])
                                delete select.arr[ranItem];
                            }else{
                                ranItem = Math.floor(Math.random() * (1+select.arr.length-1));
                                temp.push(select.arr[ranItem])
                                delete select.arr[ranItem];
                            }
                        }
                    }
                }
                 //$scope.filter = {};
                 $scope.responses = $scope.filteredItems = temp;
                // $scope.filteredItems=temp;
                // return $scope.responses;

        }
        else if (select !== undefined && select.reset!=undefined && $scope.config!==undefined) {
            $scope.filter = {};
            $http.get('/extra/report/' + $scope.config).then(function (response) {
                $scope.responses = response.data;
                $scope.QuestionName = $scope.responses[0].question;
                $scope.correctAnswer = $scope.responses[0].correctAnswer;
                $scope.CountCorrectAnswers = 0;
                angular.forEach($scope.responses, function (value, key) {
                    if (value.answerId == value.correctAnswer)
                        $scope.CountCorrectAnswers = $scope.CountCorrectAnswers + 1;
                });
                console.log("status:" + response.status);
            }).catch(function (response) {
                console.error('Error occurred:', response.status, response.data);
            }).finally(function () {
                console.log("Task Finished.");
            });
        }
        else {
            $scope.filter = {};
        }
        // {addressLineCity:citySelected} | filter:{isWin:cNoWin} | filter:{answerId:cAnswer}
    };
    $scope.getexcell = function () {
        if ($scope.filteredItems !== undefined) {
            var items = $scope.filteredItems.slice(0);
            items.filter(function (item) {
                delete item['$$hashKey'];
            });
            JSONToCSVConvertor(items, $scope.QuestionName, true);
        }
    };

}]);
function JSONToCSVConvertor(JSONData, ReportTitle, ShowLabel) {
    // Test script to generate a file from JavaScript such
    // that MS Excel will honor non-ASCII characters.

    var dataJson = JSONData;

    var emitXmlHeader = function () {
        var headerRow = '<ss:Row>\n';
        for (var colName in dataJson[0]) {
            headerRow += '  <ss:Cell>\n';
            headerRow += '    <ss:Data ss:Type="String">';
            headerRow += colName + '</ss:Data>\n';
            headerRow += '  </ss:Cell>\n';
        }
        headerRow += '</ss:Row>\n';
        return '<?xml version="1.0"?>\n' +
            '<ss:Workbook xmlns:ss="urn:schemas-microsoft-com:office:spreadsheet">\n' +
            '<ss:Worksheet ss:Name="Sheet1">\n' +
            '<ss:Table>\n\n' + headerRow;
    };

    var emitXmlFooter = function () {
        return '\n</ss:Table>\n' +
            '</ss:Worksheet>\n' +
            '</ss:Workbook>\n';
    };

    var jsonToSsXml = function (jsonObject) {
        var row;
        var col;
        var xml;
        var data = jsonObject;

        xml = emitXmlHeader();
        for (row = 0; row < dataJson.length; row++) {
            xml += '<ss:Row>\n';

            for (col in dataJson[row]) {
                xml += '  <ss:Cell>\n';
                xml += '    <ss:Data ss:Type="String">';
                xml += dataJson[row][col] + '</ss:Data>\n';
                xml += '  </ss:Cell>\n';
            }

            xml += '</ss:Row>\n';
        }

        xml += emitXmlFooter();
        return xml;
    };
    console.log(jsonToSsXml(dataJson));

    var download = function (content, filename, contentType) {
        if (!contentType) contentType = 'application/octet-stream';
        var a = document.createElement("a");
        var blob = new Blob([content], {
            'type': contentType
        });
        a.href = window.URL.createObjectURL(blob);
        a.download = filename;
        document.body.appendChild(a);
        a.click();
        document.body.removeChild(a);
    };

    download(jsonToSsXml(dataJson), ReportTitle+'_' + GetFormattedDate() + '.xls', 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet');
}

function GetFormattedDate() {
    var todayTime = new Date();
    var month = (todayTime.getMonth() + 1);
    var day = (todayTime.getDate());
    var year = (todayTime.getFullYear());
    return month + "/" + day + "/" + year;
}

