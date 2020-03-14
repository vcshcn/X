import React, {Suspense } from 'react';
import Text from '../components/Text';

export default class DynamicComponent extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        this.path = this.props.path;
        if (!this.path) {
            return null;
        }

        const Component = React.lazy(() => import(`${this.path}`));
        return (
            <React.Fragment>
                <Suspense fallback={<div><Text name="Loading"/>...</div>}>
                    <Component {...this.props} />
                </Suspense>
            </React.Fragment>
        );

    }
}

