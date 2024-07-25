@extends('layouts.app', ['class' => 'g-sidenav-show bg-gray-100'])

@section('content')
    @include('layouts.navbars.auth.topnav', ['title' => 'Dashboard'])
    <div class="container-fluid py-4">
        <div class="row">
            <div class="col-xl-3 col-sm-6 mb-xl-0 mb-4">
                <div class="card">
                    <div class="card-body p-3">
                        <div class="row">
                            <div class="col-8">
                                <div class="numbers">
                                    <p class="text-sm mb-0 text-uppercase font-weight-bold">Scholarships</p>
                                    <h5 class="font-weight-bolder">
                                        For best 2
                                    </h5>
                                    <p class="mb-0">
                                        <!-- <span class="text-success text-sm font-weight-bolder">+55%</span> -->
                                        Best two participants get a scholarship
                                    </p>
                                </div>
                            </div>
                            <div class="col-4 text-end">
                                <div class="icon icon-shape bg-gradient-primary shadow-primary text-center rounded-circle">
                                    <i class="ni ni-money-coins text-lg opacity-10" aria-hidden="true"></i>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-xl-3 col-sm-6 mb-xl-0 mb-4">
                <div class="card">
                    <div class="card-body p-3">
                        <div class="row">
                            <div class="col-8">
                                <div class="numbers">
                                    <p class="text-sm mb-0 text-uppercase font-weight-bold">Certificates</p>
                                    <h5 class="font-weight-bolder">
                                        All participants
                                    </h5>
                                    <p class="mb-0">
                                       
                                        Every participant gets a certificate 
                                    </p>
                                </div>
                            </div>
                            <div class="col-4 text-end">
                                <div class="icon icon-shape bg-gradient-danger shadow-danger text-center rounded-circle">
                                    <i class="ni ni-world text-lg opacity-10" aria-hidden="true"></i>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-xl-3 col-sm-6 mb-xl-0 mb-4">
                <div class="card">
                    <div class="card-body p-3">
                        <div class="row">
                            <div class="col-8">
                            <div class="numbers">
                                    <p class="text-sm mb-0 text-uppercase font-weight-bold">Recognition</p>
                                    <h5 class="font-weight-bolder">
                                        All participants
                                    </h5>
                                    <p class="mb-0">
                                      Recognition in the Challenge
                                    </p>
                                </div>
                            </div>
                            <div class="col-4 text-end">
                                <div class="icon icon-shape bg-gradient-success shadow-success text-center rounded-circle">
                                    <i class="ni ni-paper-diploma text-lg opacity-10" aria-hidden="true"></i>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-xl-3 col-sm-6">
                <div class="card">
                    <div class="card-body p-3">
                        <div class="row">
                            <div class="col-8">
                            <div class="numbers">
                                    <p class="text-sm mb-0 text-uppercase font-weight-bold"> Rewards</p>
                                    <h5 class="font-weight-bolder">
                                        All participants
                                    </h5>
                                    <p class="mb-0">
                                       Participation Reward to all participants
                                    </p>
                                </div>
                            </div>
                            <div class="col-4 text-end">
                                <div class="icon icon-shape bg-gradient-warning shadow-warning text-center rounded-circle">
                                    <i class="ni ni-cart text-lg opacity-10" aria-hidden="true"></i>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
  

 <div class="container mt-7">
    <div class="alert alert-primary text-center" role="alert">
      <h3>Welcome to <br> Mathematics Challenge Web System</h3>
      <h3>You are logged in as Admin, Please choose your action</h3>
    </div>
  
 </div>

        <!-- @include('layouts.footers.auth.footer') -->
   
@endsection


