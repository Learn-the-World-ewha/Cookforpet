from urllib.request import urlopen, Request, urlretrieve
from fake_useragent import UserAgent
import json
import requests
import re
from urllib.error import URLError, HTTPError

useragent = UserAgent()

baseUrl1 = 'https://recipe.bom.co.kr/recipe/'
baseUrl2 = '/detail'

def CrawlingBetweenRanges(startRecipeId, endRecipeId):
    for i in range(startRecipeId, endRecipeId):
        print("%d번째 요리:"%i)
        PageCrawler(str(i))

def PageCrawler(recipeUrl):
    url0 = baseUrl1 + recipeUrl + baseUrl2

    headers = {
        'referer' : url0,
        'User-Agent' : useragent.chrome
    }
    url = 'https://recipe.bom.co.kr/api/v1/post?embeds=user%2Ccate_detail%2Cop_function%2Cop_difficulty&filters=status=1,id='+recipeUrl

    response = urlopen(Request(url, headers=headers)).read().decode('utf-8')

    try:
        #재료목록
        res = json.loads(response)['result'][0]['materials']
        found = re.findall('me\":\"(.+?)\",\"qu',res)
        for i in range(len(found)):
                print(found[i].encode('utf-8').decode('unicode_escape'))

        #조리순서
        res2 = json.loads(response)['result'][0]['steps']
        found2 = re.findall('tent":"(.+?)"',res2)
        for i in range(len(found2)):
                print(found2[i].encode('utf-8').decode('unicode_escape'))
                
        #재료사진
        res = json.loads(response)['result'][0]['materials']
        found = re.findall('\"images\":\"(.+?)\"',res)

        print("재료 사진:")
        for i in range(len(found)):
            imgUrl = "https://recipe.bom.co.kr/uploads/"+found[i]
            filename = found[i].split('/')[-1] #기존 파일 이름 추출
            savePath = "C:/Users/User/Desktop/CapstoneProject/images"+'/'+filename
            download_file = requests.get(imgUrl, allow_redirects=True)
            photo = open(savePath, 'wb')
            photo.write(download_file.content)
            photo.close()

        #순서사진
        res2 = json.loads(response)['result'][0]['steps']
        found = re.findall('\"images\":\"(.+?)\"',res)
        print("레시피 사진:")
        for i in range(len(found)):
            print("https://recipe.bom.co.kr/uploads/"+found[i])
            
    except HTTPError as e:
        err = e.read()
        code = e.getcode()
        print(code)
    except(IndexError):
        print("레시피가 없습니다.")
        return
    
if __name__ == '__main__':
    CrawlingBetweenRanges(992,996)