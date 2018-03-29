const path = require('path');

module.exports = {
    entry: './src/index.tsx',
    devtool: 'inline-source-map',
    cache: true,
    module: {
        rules: [
            {
                test: /\.tsx?$/,
                use: 'ts-loader',
                exclude: /node_modules/
            },
            {
                test: /\.svg$/,
                loaders: [
                    'babel-loader',
                    {
                        loader: 'react-svg-loader',
                        query: {
                            jsx: true
                        }
                    },
                ]
            },
            {
                test: /\.css$/,
                use: 'css-loader',
                exclude: /node_modules/
            },
        ]
    },
    resolve: {
        extensions: ['.tsx', '.ts', '.js']
    },
    output: {
        filename: 'bundle.js',
        path: path.resolve(__dirname, '..', 'resources', 'static', 'js')
    }
};