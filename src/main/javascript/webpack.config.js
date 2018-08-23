const path = require('path');
const MiniCssExtractPlugin = require('mini-css-extract-plugin');
const VueLoaderPlugin = require('vue-loader/lib/plugin');

module.exports = {
    mode: process.env.WEBPACK_SERVE ? 'development' : 'production',
    entry: {
        index: './src/main/javascript/index.js',
        login: './src/main/javascript/login/login.js'
    },
    output: {
        filename: '[name].js',
        path: path.resolve(__dirname, '../resources/static/dist'),
        publicPath: '/dist/'
    },
    module: {
        rules: [
            {
                test: /\.vue$/,
                loader: 'vue-loader'
            },{
                test: /\.(sa|sc|c)ss$/,
                use: [{ loader: MiniCssExtractPlugin.loader }, 'css-loader', 'sass-loader']
            }
        ]
    },
    plugins: [new VueLoaderPlugin(), 
        new MiniCssExtractPlugin({ filename: "[name].css", chunkFilename: "[id].css" })],
    serve: {
        devMiddleware: {
            publicPath: '/dist/'
        }
    }
};