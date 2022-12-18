pipeline {
    agent any

    stages {
        stage('Hello') {
            steps {
                echo 'Hello World'
            }
        }
    }
    post {
        success {
            mail subject: "'${env.JOB_NAME} [${env.BUILD_NUMBER}]' Success",
            body: """
                <div id="content">
                <h1>Jenkins Nightly Report</h1>
                <div id="sum2">
                    <h2>Jenkins 运行结果</h2>
                    <ul>
                    <li>jenkins的执行结果 : <a>jenkins 执行成功</a></li>
                    <li>jenkins的Job名称 : <a id="url_1">${env.JOB_NAME} [${env.BUILD_NUMBER}]</a></li>
                    <li>jenkins的URL : <a href='${env.BUILD_URL}'>${env.BUILD_URL}</a></li>
                    <li>jenkins项目名称 : <a>${env.JOB_NAME}</a></li>
                    <li>Job URL : <a href='${env.BUILD_URL}'>${env.BUILD_URL}</a></li>
                    <li>构建日志：<a href="${BUILD_URL}console">${BUILD_URL}console</a></li>
                    </ul>
                """,
            charset: 'utf-8',
            mimeType: 'text/html',
            to: "yudong_si@163.com"
         }
        failure {
            mail subject: "'${env.JOB_NAME} [${env.BUILD_NUMBER}]' 执行失败",
            body: """
            <div id="content">
            <h1>CI报告</h1>
            <div id="sum2">
                <h2>Jenkins 运行结果</h2>
                <ul>
                <li>jenkins的执行结果 : <a>jenkins 执行失败</a></li>
                <li>jenkins的Job名称 : <a id="url_1">${env.JOB_NAME} [${env.BUILD_NUMBER}]</a></li>
                <li>jenkins的URL : <a href='${env.BUILD_URL}'>${env.BUILD_URL}</a></li>
                <li>jenkins项目名称 : <a>${env.JOB_NAME}</a></li>
                <li>Job URL : <a href='${env.BUILD_URL}'>${env.BUILD_URL}</a></li>
                <li>构建日志：<a href="${BUILD_URL}console">${BUILD_URL}console</a></li>
                </ul>
            """,
            charset: 'utf-8',
            mimeType: 'text/html',
            to: "yudong_si@163.com"
        }
    }
}
